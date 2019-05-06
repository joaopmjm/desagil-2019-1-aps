package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nand0;
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;


    public HalfAdder() {
        super("HALF-ADDER", 2, 2);

        nand0 = new NandGate();
        nand1 = new NandGate();
        nand1.connect(1, nand0);
        nand2 = new NandGate();
        nand2.connect(0, nand0);
        nand3 = new NandGate();
        nand3.connect(0, nand1);
        nand3.connect(1, nand2);
        nand4 = new NandGate();
        nand4.connect(0, nand0);
        nand4.connect(1, nand0);

    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin == 0) {
            return nand3.read();
        } else if (outputPin == 1) {
            return nand4.read();
        } else {
            throw new IndexOutOfBoundsException(outputPin);
        }
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin == 0) {
            nand0.connect(0, emitter);
            nand1.connect(0, emitter);
        } else {
            nand0.connect(1, emitter);
            nand2.connect(1, emitter);
        }
    }
}
