/*
 * Copyright (C) 2017. Miroslav Kopecky
 * This LegoPlatformMessageCodec.java  is part of robo4j.
 * path: /Users/miroslavkopecky/GiTHub_MiroKopecky/robo4j-j1kids/src/main/java/com/robo4j/lego/j1kids/example/codec/LegoPlatformMessageCodec.java
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

package com.robo4j.lego.j1kids.example.codec;

import com.robo4j.core.httpunit.HttpDecoder;
import com.robo4j.core.httpunit.HttpEncoder;
import com.robo4j.core.httpunit.HttpProducer;
import com.robo4j.core.httpunit.codec.SimpleCommand;
import com.robo4j.core.httpunit.codec.SimpleCommandCodec;
import com.robo4j.units.lego.enums.LegoPlatformMessageTypeEnum;

/**
 *
 *
 * @author Marcus Hirt (@hirt)
 * @author Miro Wengner (@miragemiko)
 */

@HttpProducer
public class LegoPlatformMessageCodec
		implements HttpDecoder<LegoPlatformMessageTypeEnum>, HttpEncoder<LegoPlatformMessageTypeEnum> {
	private final SimpleCommandCodec codec = new SimpleCommandCodec();

	@Override
	public LegoPlatformMessageTypeEnum decode(String json) {
		final SimpleCommand simpleCommand = codec.decode(json);
		return LegoPlatformMessageTypeEnum.getByName(simpleCommand.getValue());
	}

	@Override
	public Class<LegoPlatformMessageTypeEnum> getDecodedClass() {
		return LegoPlatformMessageTypeEnum.class;
	}

	@Override
	public String encode(LegoPlatformMessageTypeEnum legoPlatformMessageTypeEnum) {
		final SimpleCommand simpleCommand = new SimpleCommand(legoPlatformMessageTypeEnum.getName());
		return codec.encode(simpleCommand);
	}

	@Override
	public Class<LegoPlatformMessageTypeEnum> getEncodedClass() {
		return LegoPlatformMessageTypeEnum.class;
	}
}
