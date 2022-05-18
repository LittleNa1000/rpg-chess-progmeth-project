package unit;

import base.BaseUnit;
import base.Debuffable;
import constant.UnitConstant;
import logic.SquareOwnerState;

public class FreezerUnit extends BaseUnit implements Debuffable {
    private int stunRound;

    public FreezerUnit(SquareOwnerState state) {
        super(UnitConstant.FREEZER_UNIT_NAME, UnitConstant.FREEZER_UNIT_MAX_HP, 0,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.FREEZER_UNIT_IMAGE_1
                        : UnitConstant.FREEZER_UNIT_IMAGE_2,
                UnitConstant.FREEZER_UNIT_IMAGE_MOVE, UnitConstant.FREEZER_UNIT_IMAGE_ATTACK);
        this.stunRound = UnitConstant.FREEZER_UNIT_STUN_ROUND;
    }

    @Override
    public void debuffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setStunRoundLeft(Math.max(unit.getStunRoundLeft(), this.stunRound + 1));
    }

    public int getStunRound() {
        return stunRound;
    }

    public void setStunRound(int stunRound) {
        this.stunRound = stunRound;
    }
}
