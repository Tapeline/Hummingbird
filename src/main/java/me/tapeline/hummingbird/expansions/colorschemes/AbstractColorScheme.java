package me.tapeline.hummingbird.expansions.colorschemes;

import java.awt.Color;

public class AbstractColorScheme {
    public TextStyle regular = new TextStyle();
    public TextStyle error = (new TextStyle(regular)).under().bg(new Color(255, 0, 0, 30));
    public TextStyle warning = (new TextStyle(regular)).bg(new Color(255, 255, 0, 30));
    public TextStyle codeStyleWarning = (new TextStyle(regular)).bg(new Color(55, 255, 250, 30));
    public TextStyle deprecated = (new TextStyle(regular)).under().bg(new Color(255, 25, 250, 30));
    public TextStyle comment = (new TextStyle()).italic().fg(new Color(114, 115, 122));
    public TextStyle commentDoc = (new TextStyle()).italic().bold().fg(new Color(114, 115, 122));
    public TextStyle keyword = (new TextStyle()).bold().fg(new Color(200, 119, 50));
    public TextStyle keyword2 = (new TextStyle()).fg(new Color(148, 89, 37));
    public TextStyle operator = (new TextStyle()).fg(new Color(200, 119, 50));
    public TextStyle string = (new TextStyle()).fg(new Color(98, 134, 88));
    public TextStyle number = (new TextStyle()).fg(new Color(76, 131, 184));
    public TextStyle metadata = (new TextStyle()).fg(new Color(183, 180, 42));
    public TextStyle variable = (new TextStyle()).fg(new Color(152, 118, 170));

}
