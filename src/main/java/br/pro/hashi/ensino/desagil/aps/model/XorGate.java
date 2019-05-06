package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
<<<<<<< HEAD
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;
    private final NandGate nand4;

    public XorGate() {
        super(1);
        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();
        nand4 = new NandGate();

        nand2.connect(1, nand1);
        nand3.connect(0, nand1);
        nand4.connect(0, nand2);
        nand4.connect(1, nand3);
    }

    @Override
    public boolean read() {
        return nand4.read();
    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin != 0 & inputPin != 1) {
            throw new IndexOutOfBoundsException(inputPin);
        }
        if (inputPin == 0) {
            nand1.connect(0, emitter);
            nand2.connect(0, emitter);
        } else if (inputPin == 1) {
            nand1.connect(1, emitter);
            nand3.connect(1, emitter);
=======
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandBottom;
    private final NandGate nandRight;


    public XorGate() {
        super("XOR", 2, 1);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandBottom = new NandGate();
        nandBottom.connect(0, nandLeft);

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
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
>>>>>>> upstream/master
        }
    }
}
