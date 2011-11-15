package com.rixon.tsp.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListDataListener;

import com.rixon.tsp.Algorithm;
import com.rixon.tsp.City;
import com.rixon.tsp.Path;
import com.rixon.tsp.TravellingSalesmanSolution;
import com.rixon.tsp.impl.AlgorithmProvider;
import com.rixon.tsp.impl.ProblemFacadeImpl;
import com.rixon.tsp.impl.XYCity.CityBuilder;

public class MainView extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel controlPanel;
	private JLabel pathLabel;
	private JPanel mapPanel;
	private JPanel contentPanel;
	private JPanel mapDetailsPanel;
	private JComboBox algorithms;
	private JComboBox pathsForSolution;
	private PathComboBoxModel modelForPaths;
	private ProblemFacadeImpl facade;
	private CityBuilder builder;
	private Graphics2D graphics;
	static int cityCounter;
	
	public MainView()
	{
		super();
		super.setTitle("Travelling Salesman Problem");
		facade = new ProblemFacadeImpl();
		builder = new CityBuilder();
		//TODO how to input start and end cities
		addStartAndEndCities();
		createContentPanel();
	}

	private void addStartAndEndCities() {
		String cityName  = "C["+(cityCounter++)+"]";
		City city = builder.setStart(true).setEnd(false)
			   .setName(cityName).setX(0)
			   .setY(0).build();
		facade.addCity(city);
		city = builder.setStart(false).setEnd(true)
		   .setName("E99").setX(750)
		   .setY(550).build();
		facade.addCity(city);
	}

	private void createContentPanel() {
		initializeControlPanel();
		initializeMapPanel();
		initializeMapDetailsPanel();
		initializeContentPanel();
        setFrameProperties();
		setGraphicsProperties();
	}

	private void initializeControlPanel() {
		controlPanel = new JPanel();
		controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		controlPanel.setPreferredSize(new Dimension(800,50));
		JButton solveButton = createSolveButton();
		controlPanel.add(solveButton);
		JButton clearButton = createClearButton();
		controlPanel.add(clearButton);
	}

	private JButton createSolveButton() {
		JButton solveButton = new JButton("Solve Problem");
		solveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(algorithms.getSelectedItem());
				Algorithm algorithm = AlgorithmProvider.getNamedAlgorithm((String)algorithms.getSelectedItem());
				TravellingSalesmanSolution solution = facade.solve(algorithm);
				modelForPaths.setPaths(solution.getAllPaths());
				pathsForSolution.validate();
				//drawMultiplePath(solution.getAllPaths());
				mapPanel.validate();
			}
		});
		return solveButton;
	}

	private JButton createClearButton() {
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mapPanel.repaint();
				pathLabel.setText("");
				facade = new ProblemFacadeImpl();
				modelForPaths.setPaths(new ArrayList());
				pathsForSolution.validate();
			}
		});
		return clearButton;
	}
	
	private void drawSolution(List<City> solvedCityList) {
		City city1=null,city2=null;
		mapPanel.paint(graphics);
		graphics.setColor(Color.green);

		if (solvedCityList!=null && solvedCityList.size()>0)
		{
			city1 = solvedCityList.get(0);
			solvedCityList.remove(0);
		}
		for (City city:solvedCityList){
			city2 = city;
			int x1,x2,y1,y2;
			x1 = city1.getCoordinate().getX();
			y1 = city1.getCoordinate().getY();
			x2 = city2.getCoordinate().getX();
			y2 = city2.getCoordinate().getY();
			graphics.setColor(Color.green);
			graphics.drawLine(x1, y1, x2, y2);
			graphics.setColor(Color.red);
			if (city1.isStart()){
				graphics.fillOval(x1, y1, 16, 12);
			}
			else
			{
				graphics.drawOval(x1, y1, 16, 12);
			}
			
			graphics.drawString("("+x1+","+y1+")", x1, y1);
			if (city2.isEnd()) {
				graphics.fillOval(x2, y2, 16, 12);	
			}
			else
			{
			   graphics.drawOval(x2, y2, 16, 12);	
			}
			
			graphics.drawString("("+x2+","+y2+")", x2, y2);			
			//TODO how to show the path length;
			String pathLength = city1.getDistance(city2).getValue();
			int xd = (x1+x2)/2;
			int yd = (y1+y2)/2;
			graphics.setColor(Color.BLUE);
			graphics.drawString(pathLength, xd, yd);
			city1=city2;
		}
	}

	private void drawMultiplePath(List<Path> paths) {
		if ((paths == null) || paths.size()==0)
			return;
		mapPanel.removeAll();
		mapPanel.paint(graphics);		
	
		//while drawing multiple maps draw all cities only once t
		List<City> cities = paths.get(0).getOrderedCityList();
		drawCities(cities);
		graphics.setColor(Color.green);
		for(Path path:paths) {
			drawPath(path);
		}
	}
	
	private void drawCities(List<City> cities) {
		Color origColor = graphics.getColor();
		graphics.setColor(Color.red);
		int x1,y1;
		for (City city:cities) {			
			x1 = city.getCoordinate().getX();
			y1 = city.getCoordinate().getY();
			if (city.isStart() || city.isEnd()) {
				graphics.fillOval(x1, y1, 8, 4);
			} else {
				graphics.drawOval(x1, y1, 8, 4);
			}
			graphics.drawString("("+x1+","+y1+")", x1, y1);
		}
		graphics.setColor(origColor);
	}

	private void drawPath(Path path) 
	{
		List<City> solvedCityList = path.getOrderedCityList();
		City city1=null,city2=null;
		graphics.setColor(Color.green);

		if (solvedCityList!=null && solvedCityList.size()>0)
		{
			city1 = solvedCityList.get(0);
			solvedCityList.remove(0);
		}
		for (City city:solvedCityList){
			city2 = city;
			int x1,x2,y1,y2;
			x1 = city1.getCoordinate().getX();
			y1 = city1.getCoordinate().getY();
			x2 = city2.getCoordinate().getX();
			y2 = city2.getCoordinate().getY();
			graphics.setColor(Color.green);
			graphics.drawLine(x1, y1, x2, y2);
//			String pathLength = city1.getDistance(city2).getValue();
//			int xd = (x1+x2)/2;
//			int yd = (y1+y2)/2;
			graphics.setColor(Color.BLUE);
//			graphics.drawString(pathLength, xd, yd);
			city1=city2;
		}
	}
	
	private void initializeMapPanel() {
		mapPanel = new JPanel();
		mapPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mapPanel.setPreferredSize(new Dimension(800,600));
		mapPanel.add(new JLabel("Map"));
		mapPanel.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String cityName  = "C["+(cityCounter++)+"]";
				City city = builder.setStart(false).setEnd(false)
					   .setName(cityName).setX(e.getX())
					   .setY(e.getY()).build();
				facade.addCity(city);
				//display the point
				drawPoint(city);
			}
		});
	}

	private void drawPoint(City city) {
		graphics.fillOval(city.getCoordinate().getX(), city.getCoordinate().getY(), 16, 12);
		graphics.drawString("("+city.getCoordinate().getX()+","+city.getCoordinate().getY()+")", 
				city.getCoordinate().getX(), city.getCoordinate().getY());
	}

	private void initializeMapDetailsPanel() {
		mapDetailsPanel = new JPanel();
		mapDetailsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mapDetailsPanel.setPreferredSize(new Dimension(800,50));
		mapDetailsPanel.add(new JLabel("Select Algorithm"));
		algorithms = new JComboBox();
		mapDetailsPanel.add(algorithms);
		algorithms.setModel(new ComboBoxModel() {
			
			String selectedEntry;
			@Override
			public void removeListDataListener(ListDataListener l) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public int getSize() {
				// TODO Auto-generated method stub
				return 2;
			}
			
			@Override
			public Object getElementAt(int index) {
				switch(index){
				case 0  : return AlgorithmProvider.NEAREST_CITY_ONLY;
				case 1  : return AlgorithmProvider.ALL_PERMUTATIONS;
				default : return "Unknown";
				}
			}
			
			@Override
			public void addListDataListener(ListDataListener l) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void setSelectedItem(Object anItem) {
				selectedEntry = (String)anItem;
			}
			
			@Override
			public Object getSelectedItem() {
				return selectedEntry;
			}
		});
		mapDetailsPanel.add(new JLabel("Path Length"));
		pathLabel = new JLabel();
		mapDetailsPanel.add(pathLabel);
		pathsForSolution = new JComboBox();
		pathsForSolution.setPreferredSize(new Dimension(120, 20));
		modelForPaths = new PathComboBoxModel();
		pathsForSolution.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox j= (JComboBox)e.getSource();
				Path p  = (Path)j.getSelectedItem();
				drawSolution(p.getOrderedCityList());
				mapPanel.validate();
			}
		});
		pathsForSolution.setModel(modelForPaths);
		mapDetailsPanel.add(pathsForSolution);
	}
	
	private void initializeContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
		contentPanel.add(controlPanel);
		contentPanel.add(mapPanel);
		contentPanel.add(mapDetailsPanel);
		setContentPane(contentPanel);
	}

	private void setFrameProperties() {
		setSize(800, 650);
        setResizable(false);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
	}
	
	private void setGraphicsProperties() {
		graphics = (Graphics2D)mapPanel.getGraphics();
		graphics.setFont(new Font("Tahoma",Font.PLAIN,8));
		graphics.setStroke(new BasicStroke(
			      2f, 
			      BasicStroke.CAP_ROUND, 
			      BasicStroke.JOIN_ROUND, 
			      2F, 
			      new float[] {3f}, 
			      0f));

	}
	
	public static void main(String[] args)
	{
		 //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	new MainView();
            }
        });
	}
	
	private class PathComboBoxModel implements ComboBoxModel {

		private List<Path> paths;
		private Path selectedPath;
		
		PathComboBoxModel() {
			paths = new ArrayList<Path>();
		}
		
		void setPaths(List<Path> paths) {
			//TODO need to find a way to empty the current list and then add the new path
			this.paths = paths;
		}
		
		@Override
		public Object getSelectedItem() {
			return selectedPath;
		}

		@Override
		public void setSelectedItem(Object anItem) {
			selectedPath = (Path)anItem;
		}

		@Override
		public void addListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getElementAt(int index) {
			return paths.get(index);
		}

		@Override
		public int getSize() {
			return paths.size();
		}

		@Override
		public void removeListDataListener(ListDataListener l) {
			// TODO Auto-generated method stub
		}
		
	}
}
