/*
 * Copyright (C) 2017. Miroslav Kopecky
 * This Number42Main.java  is part of robo4j.
 * path: /Users/miroslavkopecky/GiTHub_MiroKopecky/robo4j-j1kids/src/main/java/com/robo4j/lego/j1kids/example/Number42Main.java
 * module: robo4j-j1kids_main
 *
 * robo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * robo4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with robo4j .  If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.lego.j1kids.example;

import com.robo4j.core.RoboBuilder;
import com.robo4j.core.RoboBuilderException;
import com.robo4j.core.RoboContext;
import com.robo4j.core.RoboReference;
import com.robo4j.core.client.util.RoboClassLoader;
import com.robo4j.hw.lego.util.EscapeButtonUtil;

import java.io.IOException;

/**
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */
public class Number42Main {

    public static void main(String[] args) throws RoboBuilderException, IOException {
        Number42Main robot = new Number42Main();
        RoboContext system = robot.init();
        robot.shutdown(system);
    }

    private Number42Main() {

    }

    RoboContext init() throws RoboBuilderException, IOException {
        RoboBuilder builder = new RoboBuilder().add(RoboClassLoader.getInstance().getResource("robo4j.xml"));
        RoboContext system = builder.build();
        system.start();

        RoboReference<String> lcd = system.getReference("lcd");
        lcd.sendMessage("Robo4J.io");

        return system;
    }

    void shutdown(RoboContext system) {
        EscapeButtonUtil.waitForPressAndRelease();
        system.shutdown();
    }
}
