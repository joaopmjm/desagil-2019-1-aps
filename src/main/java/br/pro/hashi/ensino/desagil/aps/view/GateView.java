package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class GateView extends FixedPanel implements ItemListener, MouseListener {
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;
    private Light light;
    private final Image image;

    public GateView(Gate gate) {
        super(245, 150);
        this.gate = gate;
        this.light = new Light();

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];

        String name = gate.toString() + ".jpeg";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);
        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);
        }

        int ya = 22;
        for (JCheckBox inputBox : inputBoxes) {
            if (inputSize == 1) {
                add(inputBox, 10, 40, 25, 25);
            } else {
                add(inputBox, 10, ya, 25, 25);
                ya += 40;
            }
        }

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }
        update();
    }

    private void update() {
        light.connect(0,gate);
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }
        boolean result = gate.read();
        if (result == true) {
            light.setR(255);
        } else{
            light.setR(0);
        }
        Color color = new Color(light.getR(),light.getG(),light.getB());
        repaint();
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Color color = new Color(light.getR(),light.getG(),light.getB());
        if (x > 210 && x < 235 && y > 45 && y < 70){
                color = JColorChooser.showDialog(this, null, color);
                light.setR(color.getRed());
                light.setG(color.getGreen());
                light.setB(color.getBlue());
                repaint();
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 25, 0, 175, 110, this);
        Color color = new Color(light.getR(),light.getG(),light.getB());
        g.setColor(color);
        g.fillRoundRect(210, 45, 25, 25, 25, 25);
        getToolkit().sync();
    }
}
