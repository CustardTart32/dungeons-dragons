package unsw.dungeon;

import java.util.List;

public class MovementAway implements Movement{
	public void calculateMove(List<String> directions, int x_distance, int y_distance) {
		if (x_distance >= 0 && y_distance >= 0) {
			if (x_distance >= y_distance) {
				directions.add("left");
				directions.add("down");
				directions.add("up");
				directions.add("right");
			} else {
				directions.add("down");
				directions.add("left");
				directions.add("right");
				directions.add("up");
			}
		}
		else if (x_distance >= 0 && y_distance <= 0 ) {
			if (Math.abs(x_distance) >= Math.abs(y_distance)) {
				directions.add("left");
				directions.add("up");
				directions.add("down");
				directions.add("right");
			} else {
				directions.add("up");
				directions.add("left");
				directions.add("right");
				directions.add("down");
			}
		}
		else if (x_distance <= 0 && y_distance >= 0) {
			if (Math.abs(x_distance) >= Math.abs(y_distance)) {
				directions.add("right");
				directions.add("down");
				directions.add("up");
				directions.add("left");
			} else {
				directions.add("down");
				directions.add("right");
				directions.add("left");
				directions.add("up");
			}

		}
		else {
			if (Math.abs(x_distance) >= Math.abs(y_distance)) {
				directions.add("right");
				directions.add("up");
				directions.add("down");
				directions.add("left");
			} else {
				directions.add("up");
				directions.add("right");
				directions.add("left");
				directions.add("down");
			}
		}
	}
}
