package unit;

import base.BaseUnit;
import base.Debuffable;
import constant.UnitConstant;
import logic.BoardSquareState;

public class VenomUnit extends BaseUnit implements Debuffable {
    private final int poisonPower;
    private int poisonRound;

    public VenomUnit(BoardSquareState state) {
        super(UnitConstant.VENOM_UNIT_NAME, UnitConstant.VENOM_UNIT_MAX_HP, 0,
                state == BoardSquareState.PLAYER1 ? UnitConstant.VENOM_UNIT_IMAGE_URL_1
                        : UnitConstant.VENOM_UNIT_IMAGE_URL_2);
        this.poisonPower = UnitConstant.VENOM_UNIT_POISON_POWER;
        this.poisonRound = UnitConstant.VENOM_UNIT_POISON_ROUND;
    }

    @Override
    public void debuffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setVenomRoundLeft(poisonRound);
    }

    public int getPoisonPower() {
        return poisonPower;
    }

    public int getPoisonRound() {
        return poisonRound;
    }

    public void setPoisonRound(int poisonRound) {
        this.poisonRound = poisonRound;
    }
}
