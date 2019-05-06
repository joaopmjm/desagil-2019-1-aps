package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
<<<<<<< HEAD
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;

    public OrGate() {
        super(2);
        this.nand1 = new NandGate();
        this.nand2 = new NandGate();
        this.nand3 = new NandGate();

        nand3.connect(1, nand1);
        nand3.connect(0, nand2);
    }

    @Override
    public boolean read() {
        return nand3.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin != 0 & inputPin != 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        if (inputPin == 0) {
            nand1.connect(0, emitter);
            nand1.connect(1, emitter);
        } else if (inputPin == 1) {
            nand2.connect(0, emitter);
            nand2.connect(1, emitter);
        }
    }
}
=======
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandRight;


    public OrGate() {
        super("OR", 2, 1);

        nandTop = new NandGate();

        nandBottom = new NandGate();

        nandRight = new NandGate();
        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandTop.connect(1, emitter);
                break;
            case 1:
                nandBottom.connect(0, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
>>>>>>> upstream/master
