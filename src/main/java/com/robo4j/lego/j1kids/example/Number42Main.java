/*
 * Copyright (c) 2014, 2017, Marcus Hirt, Miroslav Wengner
 *
 * Robo4J is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Robo4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Robo4J. If not, see <http://www.gnu.org/licenses/>.
 */

package com.robo4j.lego.j1kids.example;

import com.robo4j.core.RoboBuilder;
import com.robo4j.core.RoboContext;
import com.robo4j.core.RoboReference;
import com.robo4j.hw.lego.util.EscapeButtonUtil;
import com.robo4j.units.lego.enums.LegoPlatformMessageTypeEnum;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.StandardMBean;
import java.lang.management.ManagementFactory;

/**
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */
public class Number42Main {

    public static void main(String[] args) throws Exception {
        Number42Main robot = new Number42Main();
        RoboContext system = robot.init();
        robot.shutdown(system);
    }

    private Number42Main() {

    }

    private RoboContext init() throws Exception {
        RoboBuilder builder = new RoboBuilder().add(Thread.currentThread().getContextClassLoader().getResourceAsStream("robo4j.xml"));
        RoboContext system = builder.build();
        system.start();
        initMBeanServer(system);

        RoboReference<String> lcd = system.getReference("lcd");
        lcd.sendMessage("Robo4J.io");

        return system;
    }

    private void shutdown(RoboContext system) {
        EscapeButtonUtil.waitForPressAndRelease();
        system.shutdown();
    }

    private void initMBeanServer(RoboContext system) throws Exception {
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        RoboReference<LegoPlatformMessageTypeEnum> cReference = system.getReference("controller");
        ObjectName cName = ObjectName.getInstance("com.robo4j.lego.j1kids.example.controller:type=PlatformController");
        StandardMBean mBean = new StandardMBean(cReference, RoboReference.class);
        mBeanServer.registerMBean(mBean, cName);



    }

}
