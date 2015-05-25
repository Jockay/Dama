package gui;

import javax.swing.JButton;

import model.*;

@SuppressWarnings("serial")
public class DamaButton extends JButton {
	private boolean selected;
	private Coordinate coordinate;
	
	public DamaButton(Coordinate c) {
		super("");
		this.selected = false;
		this.coordinate = c;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	public DamaButton getThisButton() {
		return this;
	}

	@Override
	public String toString() {
		return "DamaButton [selected=" + selected + ", coordinate="
				+ coordinate + "]";
	}
	
	
}
