package unit;

import base.Attackable;
import base.BaseUnit;
import constant.UnitConstant;
import logic.BoardSquareState;

public class FlyingUnit extends BaseUnit implements Attackable {
    public FlyingUnit(BoardSquareState state) {
        super(UnitConstant.FLYING_UNIT_NAME, UnitConstant.FLYING_UNIT_MAX_HP, UnitConstant.FLYING_UNIT_POWER,
                state == BoardSquareState.PLAYER1 ? UnitConstant.FLYING_UNIT_IMAGE_URL_1
                        : UnitConstant.FLYING_UNIT_IMAGE_URL_2);
    }

    @Override
    public void attackUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.reduceHealth(this.getPower());
    }
}
