package unit;

import base.BaseUnit;
import base.Buffable;
import constant.UnitConstant;
import logic.BoardSquareState;

public class HealerUnit extends BaseUnit implements Buffable {
    private int healingPoint;

    public HealerUnit(BoardSquareState state) {
        super(UnitConstant.HEALER_UNIT_NAME, UnitConstant.HEALER_UNIT_MAX_HP, 0,
                state == BoardSquareState.PLAYER1 ? UnitConstant.HEALER_UNIT_IMAGE_URL_1
                        : UnitConstant.HEALER_UNIT_IMAGE_URL_2);
        this.healingPoint = UnitConstant.HEALER_UNIT_HEALING_POINT;
    }

    @Override
    public void buffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        setCurrentHealth(Math.min(unit.getMaxHealth(), unit.getCurrentHealth() + healingPoint));
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    public void setHealingPoint(int healingPoint) {
        this.healingPoint = healingPoint;
    }
}
