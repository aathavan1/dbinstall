package com.aathavan.dbinstall.common;

import javax.swing.*;

public class DbInstallCommon {

    public static int horizontalGap(JPanel jPanel, JComponent jComponent, double per) {
        return (int) (jComponent.getX() + jComponent.getWidth() + (jPanel.getWidth() * per / 100));
    }

    public static int verticalGap(JPanel jPanel, JComponent jComponent, double per) {
        return (int) (jComponent.getY() + jComponent.getHeight() + (jPanel.getHeight() * per / 100));
    }
}
