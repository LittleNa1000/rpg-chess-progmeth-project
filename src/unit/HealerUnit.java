package unit;

import base.BaseUnit;
import base.Buffable;
import constant.PotionConstant;
import constant.UnitConstant;
import logic.SquareOwnerState;

public class HealerUnit extends BaseUnit implements Buffable {
    private int healingPoint;

    public HealerUnit(SquareOwnerState state) {
        super(UnitConstant.HEALER_UNIT_NAME, UnitConstant.HEALER_UNIT_MAX_HP, 0,
                state == SquareOwnerState.PLAYER1 ? UnitConstant.HEALER_UNIT_IMAGE_1
                        : UnitConstant.HEALER_UNIT_IMAGE_2,
                UnitConstant.HEALER_UNIT_IMAGE_MOVE, UnitConstant.HEALER_UNIT_IMAGE_ATTACK);
        this.healingPoint = UnitConstant.HEALER_UNIT_HEALING_POINT;
    }

    @Override
    public void upgrade() {
        healingPoint += PotionConstant.BUFF_POTION_HEALING_POINT;
    }

    @Override
    public void buffUnit(BaseUnit unit) {
        // TODO Auto-generated method stub
        unit.setCurrentHealth(Math.min(unit.getMaxHealth(), unit.getCurrentHealth() + healingPoint));
    }

    public int getHealingPoint() {
        return healingPoint;
    }

    public void setHealingPoint(int healingPoint) {
        this.healingPoint = healingPoint;
    }
}
