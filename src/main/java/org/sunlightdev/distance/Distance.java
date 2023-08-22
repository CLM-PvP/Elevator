package org.sunlightdev.distance;

/*

Lukas - 18:28
22.08.2023
https://github.com/NightDev701

© SunLightScorpion 2020 - 2023

*/

import org.bukkit.Location;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Objects;
import java.util.Vector;

public class Distance {

        private final Location to;
        private final Location from;
        private final double xDiff;
        private final double yDiff;
        private final double zDiff;
        private final boolean goingUp;
        private final boolean goingDown;
        String direct, heightDefinition;

        public Distance(PlayerMoveEvent e) {
            this(e.getFrom(), Objects.requireNonNull(e.getTo()));
        }

        public Distance(PlayerTeleportEvent e) {
            this(e.getFrom(), Objects.requireNonNull(e.getTo()));
        }

        public Distance(Location a, Location b) {
            this.from = a;
            this.to = b;

            this.xDiff = Math.abs(a.getX() - b.getX());
            this.yDiff = Math.abs(a.getY() - b.getY());
            this.zDiff = Math.abs(a.getZ() - b.getZ());
            goingUp = to.getY() > from.getY();
            goingDown = from.getY() > to.getY();
        }

        public Location getTo() {
            return to;
        }

        public Location getFrom() {
            return from;
        }

        public double getXDifference() {
            return xDiff;
        }

        public double getZDifference() {
            return zDiff;
        }

        public double getYDifference() {
            return yDiff;
        }

        public boolean isGoingDown() {
            return goingDown;
        }

        public boolean isGoingUp() {
            return goingUp;
        }

        public boolean isMovingHorizontally() {
            return xDiff != 0 || zDiff != 0;
        }
}