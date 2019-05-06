package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
<<<<<<< HEAD
    private final NandGate nand1;
    private final NandGate nand2;

    public AndGate() {
        super(2);
        this.nand1 = new NandGate();
        this.nand2 = new NandGate();

        nand2.connect(0, nand1);
        nand2.connect(1, nand1);

    }

    @Override
    public boolean read() {
        return nand2.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin != 0 & inputPin != 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        if (inputPin == 0) {
            nand1.connect(0, emitter);
        } else if (inputPin == 1) {
            nand1.connect(1, emitter);
        }
=======
    private final NandGate nandLeft;
    private final NandGate nandRight;


    public AndGate() {
        super("AND", 2, 1);

        nandLeft = new NandGate();

        nandRight = new NandGate();
        nandRight.connect(0, nandLeft);
        nandRight.connect(1, nandLeft);
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
        if (inputPin < 0 || inputPin > 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        nandLeft.connect(inputPin, emitter);
>>>>>>> upstream/master
    }
}
