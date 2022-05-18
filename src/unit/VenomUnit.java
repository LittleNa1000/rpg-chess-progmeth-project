package unit;

import base.BaseUnit;
import base.Debuffable;
import constant.UnitConstant;
import logic.SquareOwnerState;

public class VenomUnit extends BaseUnit implements Debuffable {
    private static final int poisonPower = UnitConstant.VENOM_UNIT_POISON_POWER;
    private int poisonRound;

    public VenomUnit(SquareOwnerState state) {
        super(UnitConstant.VENOM_UNIT_NAME, UnitConstant.VENOM_UNIT_MAX_HP, 0,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.VENOM_UNIT_IMAGE_1
                        : UnitConstant.VENOM_UNIT_IMAGE_2,
                UnitConstant.VENOM_UNIT_IMAGE_MOVE, UnitConstant.VENOM_UNIT_IMAGE_ATTACK);
        this.poisonRound = UnitConstant.VENOM_UNIT_POISON_ROUND;
    }

    @Override
    public void debuffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setVenomRoundLeft(poisonRound);
    }

    public static int getPoisonpower() {
        return poisonPower;
    }

    public int getPoisonRound() {
        return poisonRound;
    }

    public void setPoisonRound(int poisonRound) {
        this.poisonRound = poisonRound;
    }
}
