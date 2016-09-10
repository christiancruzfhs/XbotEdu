package xbot.edubot.linear;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import xbot.common.math.XYPair;

public class LinearVisualizationPanel extends JPanel {
	private int centerX;
    private int centerY;
    private XYPair center;
    private double baseMagnitude;
    
    private double robotCurrentDistance = 0;

    private int preferredWidth = 500;
    private int minimumWidth = 500;

    public LinearVisualizationPanel() {

    }

    public LinearVisualizationPanel(int preferredWidth, int minimumWidth) {
        this.preferredWidth = preferredWidth;
        this.minimumWidth = minimumWidth;
    }
    
    public void setRobotDistance(double distance) {
    	robotCurrentDistance = distance;
    }
    
    public void updateViz(DriveToPositionCommandTest.LinearEnvironmentState envState) {
    	robotCurrentDistance = envState.distance;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;

        centerX = this.getWidth() / 2;
        centerY = this.getHeight() / 2;
        center = new XYPair(centerX, centerY);
        baseMagnitude = Math.min(centerX, centerY) * 0.8;
        
        int linearFactor = 30;
        
        // draw start
        graphics.drawOval(centerX, centerY, 5, 5);
        
        // draw finish
        graphics.drawOval(centerX+5*linearFactor, centerY, 5, 5);
        
        int robotLocation = (int) (robotCurrentDistance*linearFactor);
        
        // draw robot
        graphics.setColor(Color.BLUE);
        graphics.setStroke(new BasicStroke(5));
        graphics.drawOval(centerX+robotLocation, centerY, 5, 5);
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredWidth, 0);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(minimumWidth, 0);
    }
    
}