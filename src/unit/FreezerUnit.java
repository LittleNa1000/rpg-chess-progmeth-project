package unit;

import base.BaseUnit;
import base.Debuffable;
import constant.UnitConstant;
import logic.BoardSquareState;

public class FreezerUnit extends BaseUnit implements Debuffable {
    private int stunRound;

    public FreezerUnit(BoardSquareState state) {
        super(UnitConstant.FREEZER_UNIT_NAME, UnitConstant.FREEZER_UNIT_MAX_HP, 0,
                state == BoardSquareState.PLAYER1 ? UnitConstant.FREEZER_UNIT_IMAGE_URL_1
                        : UnitConstant.FREEZER_UNIT_IMAGE_URL_2);
        this.stunRound = UnitConstant.FREEZER_UNIT_STUN_ROUND;
    }

    @Override
    public void debuffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setStunRoundLeft(Math.max(unit.getStunRoundLeft(), this.stunRound));
    }

    public int getStunRound() {
        return stunRound;
    }

    public void setStunRound(int stunRound) {
        this.stunRound = stunRound;
    }
}
