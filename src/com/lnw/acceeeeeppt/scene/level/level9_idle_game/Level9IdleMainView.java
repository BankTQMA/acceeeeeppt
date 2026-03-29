package com.lnw.acceeeeeppt.scene.level.level9_idle_game;

import javax.swing.*;
import java.awt.*;

public class Level9IdleMainView extends JPanel {
    public Level9IdleMainView(Level9IdleModel level9IdleModel) {
        setLayout(new GridLayout(2, 1));

        add(new Level9IdleTosView(level9IdleModel));
        add(new Level9IdleGameView(level9IdleModel));
    }
}
