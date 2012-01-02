/*
 * Copyright (c) 2010 Griffon Slick - Andres Almiray. All Rights Reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 *  o Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 
 *  o Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 *  o Neither the name of Griffon Slick - Andres Almiray nor the names of
 *    its contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
 * OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package griffon.plugins.slick

import org.codehaus.griffon.runtime.core.BaseGriffonApplication
import griffon.swing.AbstractSwingGriffonApplication

import org.newdawn.slick.Game
import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

/**
 * 
 * @author Andres Almiray
 */
class StateBasedSlickGriffonApplication extends AbstractSwingGriffonApplication implements GriffonSlickApplication {
    private boolean gameContainerDispensed = false
    
    final GameContainer gameContainer
    final Game game

    StateBasedSlickGriffonApplication(String[] args = BaseGriffonApplication.EMPTY_ARGS) {
        super(args)
        game = new GriffonStateBasedGame(this)
        gameContainer = new GriffonAppGameContainer(this, 640, 480, false)
    }

    StateBasedGame getStateBasedGame() {
        game
    }

    void show() {
        gameContainer.start()

        callReady()
    }
    
    void exit() {
        gameContainer.quit()
        super.exit()
    }

    Object createApplicationContainer() {
        if(gameContainerDispensed) {
            super.createApplicationContainer()
        } else {
            gameContainerDispensed = true
            gameContainer
        }
    }
    
    public static void main(String[] args) {
        AbstractSwingGriffonApplication.run(StateBasedSlickGriffonApplication, args)
    }
}