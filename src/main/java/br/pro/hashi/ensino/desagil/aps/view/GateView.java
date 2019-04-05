package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

// A classe JPanel representa uma das componentes mais
// simples da Swing. A função dela é simplesmente ser
// um contêiner para colocar outras componentes dentro.
// A razão de implementar ActionListener está mais abaixo.
public class GateView extends JPanel implements ItemListener {

    // A ideia é que essa componente gráfica represente
    // uma calculadora específica. Essa calculadora que
    // está sendo representada é guardada como atributo.
    private final Gate gate;

    // A classe JTextField representa um campo de texto.
    private final JCheckBox ApinField;
    private final JCheckBox resultField;
    private JCheckBox BpinField;

    public GateView(Gate gate) {
        this.gate = gate;
        if (this.gate.getInputSize() == 1) {
            ApinField = new JCheckBox();
            resultField = new JCheckBox();

            JLabel ApinLabel = new JLabel("Pin A");
            JLabel resultLabel = new JLabel("Output");

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            add(ApinLabel);
            add(ApinField);
            add(resultLabel);
            add(resultField);

            ApinField.addItemListener(this);

            resultField.setEnabled(false);
            update();
        } else {
            // Nada de especial na construção dos campos.
            ApinField = new JCheckBox();
            BpinField = new JCheckBox();
            resultField = new JCheckBox();

            // A classe JLabel representa um rótulo, ou seja,
            // um texto não-editável que queremos colocar na
            // interface para identificar alguma coisa. Não
            // precisa ser atributo, pois não precisamos mais
            // mexer nesses objetos depois de criar e adicionar.
            JLabel ApinLabel = new JLabel("Pin A");
            JLabel BpinLabel = new JLabel("Pin B");
            JLabel resultLabel = new JLabel("Output");

            // Um JPanel tem um layout, ou seja, um padrão para
            // organizar as componentes dentro dele. A linha abaixo
            // estabelece um dos padrões mais simples: simplesmente
            // colocar uma componente debaixo da outra, alinhadas.
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // Colocamos todas componentes aqui no contêiner.
            add(ApinLabel);
            add(ApinField);
            add(BpinLabel);
            add(BpinField);
            add(resultLabel);
            add(resultField);

            // Uma campo de texto tem uma lista de observadores que
            // reagem quando o usuário dá Enter depois de digitar.
            // Usamos o método addActionListener para adicionar esta
            // instância de CalculatorView, ou seja "this", nessa
            // lista. Só que addActionListener espera receber um objeto
            // do tipo ActionListener como parâmetro. É por isso que
            // adicionamos o "implements ActionListener" lá em cima.
            ApinField.addItemListener(this);
            BpinField.addItemListener(this);

            // O último campo de texto não pode ser editável, pois é
            // só para exibição. Logo, configuramos como desabilitado.
            resultField.setEnabled(false);

            // Update é o método que definimos abaixo para atualizar o
            // último campo de acordo com os valores dos primeiros.
            // Precisamos chamar esse método no final da construção
            // para garantir que a interface não nasce inconsistente.
            update();
        }
    }

    private void update() {
        if (gate.getInputSize() == 2) {
            Switch A = new Switch();
            Switch B = new Switch();
            try {
                Object PinoA = ApinField.getSelectedObjects();
                System.out.println(PinoA);
                if (PinoA == null) {
                    A.turnOff();
                } else {
                    A.turnOn();
                }
            } catch (NullPointerException exception) {
                System.out.println("Exception");
            }
            try {
                Object PinoB = BpinField.getSelectedObjects();
                System.out.println(PinoB);
                if (PinoB == null) {
                    B.turnOff();
                } else {
                    B.turnOn();
                }
            } catch (NullPointerException exception) {
                System.out.println("Exception");
            }

            gate.connect(0, A);
            gate.connect(1, B);
            boolean resul = gate.read();
            if (resul) {
                resultField.setSelected(true);
                resultField.setEnabled(false);
            } else {
                resultField.setSelected(false);
                resultField.setEnabled(false);
            }
        } else {
            Switch A = new Switch();
            try {
                Object PinoA = ApinField.getSelectedObjects();
                System.out.println(PinoA);
                if (PinoA == null) {
                    A.turnOff();
                } else {
                    A.turnOn();
                }
            } catch (NullPointerException exception) {
                System.out.println("Exception");
            }
            gate.connect(0, A);
        }

        boolean resul = gate.read();
        if (resul) {
            resultField.setSelected(true);
            resultField.setEnabled(false);
        } else {
            resultField.setSelected(false);
            resultField.setEnabled(false);
        }

        /*
        boolean Apin;
        boolean Bpin;

        try {
            // O conteúdo de um campo é uma String, não um double.
            // Se queremos interpretar como double, precisamos fazer
            // uma conversão. Esse Double.parseDouble faz isso...
            Apin = Boolean.parseBoolean(ApinField.getText());
            Bpin = Boolean.parseBoolean(BpinField.getText());

        } catch (NumberFormatException exception) {
            // ...e se a string não representar um double válido,
            // esse parseDouble lança um NumberFormatException.
            resultField.setText("?");
            return;
        }*/

        // O contrário também vale! Para colocar um double no
        // campo, precisamos antes converter ele para String.
    }

    // O que esta componente deve fazer quando o usuário der um
    // Enter depois de digitar? Apenas chamar o update, é claro!
    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }
}
