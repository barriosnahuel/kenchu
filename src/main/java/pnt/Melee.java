/*
 * TiroLioYCoshaGolda - Just a simple Robot for battle in Robocode.
 * Copyright (C) 2013 Nahuel Barrios <barrios.nahuel@gmail.com>.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pnt;

import robocode.ScannedRobotEvent;

import java.awt.*;

/**
 * TODO : Javadoc for
 * <p/>
 * Created on 8/17/13, at 7:06 PM.
 *
 * @author Nahuel Barrios <barrios.nahuel@gmail.com>.
 */
public class Melee extends BaseRobot {

    private boolean isAttacking;

    private double targetDistance;

    @Override
    public void run() {
        super.run();

        setColors(Color.white, Color.white, Color.black);

        while (true) {
            scanForEnemies(4, false);
            ahead(ROBOT_SIZE * 4);
            scanForEnemies(4, false);
            back(ROBOT_SIZE * 4);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        double enemyDistance = event.getDistance();
        if (!isAttacking || enemyDistance < targetDistance) {
            stop();
            isAttacking = true;
            targetDistance = enemyDistance;

            turnGun(getHeading() + event.getBearing(), getGunHeading());

            handleFire(event);
            resume();
        }

        isAttacking = false;
    }
}