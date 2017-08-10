/*
 * Copyright (C) 2017. Miroslav Kopecky
 * This WeaponController.java  is part of robo4j.
 * path: /Users/miroslavkopecky/GiTHub_MiroKopecky/robo4j-j1kids/src/main/java/com/robo4j/lego/j1kids/example/controller/WeaponController.java
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

package com.robo4j.lego.j1kids.example.controller;

import com.robo4j.core.ConfigurationException;
import com.robo4j.core.RoboContext;
import com.robo4j.core.RoboUnit;
import com.robo4j.core.configuration.Configuration;
import com.robo4j.units.lego.platform.MotorRotationEnum;

/**
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */
public class WeaponController extends RoboUnit<MotorRotationEnum> {

    private String target;

    public WeaponController(RoboContext context, String id) {
        super(MotorRotationEnum.class, context, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onMessage(MotorRotationEnum message) {
        processMessage(message);
    }

    @Override
    protected void onInitialization(Configuration configuration) throws ConfigurationException {
        target = configuration.getString("target", null);
        if (target == null) {
            throw ConfigurationException.createMissingConfigNameException("target");
        }
    }

    private void processMessage(MotorRotationEnum message) {
        getContext().getReference(target).sendMessage(message);
    }

}