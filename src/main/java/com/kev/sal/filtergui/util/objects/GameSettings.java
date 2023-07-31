package com.kev.sal.filtergui.util.objects;

import com.kev.sal.filtergui.MainController.Game;

import java.io.File;

public class GameSettings {
        private File sourceFile;
        private Game gameType;
        private boolean includeHints;
        private boolean includeGivenEnds;
        private boolean includeGiven;
        private int numGiven;

        public GameSettings(File f, Game gt, boolean h, boolean ge, boolean g, int ng) {
            this.sourceFile = f;
            this.gameType = gt;
            this.includeHints = h;
            this.includeGivenEnds = h && ge;
            this.includeGiven = g;
            this.numGiven = g ? 0 : -1;
        }

        public File getSourceFile() {
            return sourceFile;
        }

        public Game getGameType() {
            return gameType;
        }

        public boolean includesHints() {
            return includeHints;
        }

        public boolean includesGivenEnds() {
            return includeGivenEnds;
        }

        public boolean includesGiven() {
            return includeGiven;
        }

        public int getNumGiven() {
            return numGiven;
        }
}
