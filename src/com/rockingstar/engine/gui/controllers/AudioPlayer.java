/*
 * Enjun
 *
 * @version     1.0 Beta 1
 * @author      Rocking Stars
 * @copyright   2018, Enjun
 *
 * Copyright 2018 RockingStars

 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rockingstar.engine.gui.controllers;

import com.rockingstar.engine.io.models.Util;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URL;
/**
 * @author Rocking Stars
 * @since  beta 1.0
 */
public class AudioPlayer extends Thread {

    private boolean _repeat;
    private MediaPlayer _mediaPlayer;

    /**
     * audio file is added to the MediaPlayer
     * @param filename filename of the audio file
     * @param repeat boolean is a audio file has to repeat or not
     */
    public AudioPlayer(String filename, boolean repeat) {
        _repeat = repeat;

        URL resource = getClass().getClassLoader().getResource("resources/sound/music/" + filename);

        Util.displayStatus("Loading music file", resource != null);

        if (resource == null)
            return;

        _mediaPlayer = new MediaPlayer(new Media(resource.toString()));
    }

    /**
     * Checks if _mediaPlayer is set, then plays the audio, and repeats if needed
     */
    @Override
    public void run() {
        if (_mediaPlayer == null)
            return;

        _mediaPlayer.play();

        if (_repeat)
            _mediaPlayer.setOnEndOfMedia(() -> _mediaPlayer.seek(Duration.ZERO));
    }

    /**
     * Starts the audio
     */
    public void play() {
        start();
    }

    /**
     * Ends the audio
     */
    public void end() {
        _mediaPlayer.stop();
    }
}
