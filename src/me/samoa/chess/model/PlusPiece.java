package me.samoa.chess.model;

/**
 * The Plus piece moves in a straight line in ANY direction.
 * It CANNOT skip over other pieces.
 */

public class PlusPiece extends Piece{
  boolean vertical = false;

  public PlusPiece(Player player, int r, int c) {
    super(player, r, c);
    type = Type.Plus;
  }

  @Override
  public void onMove(Slot slot) {
    if(isPlaceable(slot)){
      if(vertical) {
        super.setPositionR(slot.getRow());
      }
      else {
        super.setPositionC(slot.getCol());
      }
    }
  }
  
  @Override 
  public boolean isPlaceable(Slot selectedPosition) {

    if(super.getPositionR() == selectedPosition.getRow() && super.getPositionC() != selectedPosition.getCol()) {
      int distance = super.distanceCounter(super.getPositionC(), selectedPosition.getCol());
      int multiplier = (super.getPositionC() > selectedPosition.getCol()) ? 1 : -1;
      for(int i = 1; i < distance; i++){
        if(super.getBoard().getSlotOccupied(selectedPosition.getRow(), selectedPosition.getCol() + (i * multiplier)))
          return false;
      }
      System.out.println("move vertically");
      vertical = true;
      return true;
    }
    else if(super.getPositionR() != selectedPosition.getRow() && super.getPositionC() == selectedPosition.getCol()) {
      int distance = super.distanceCounter(super.getPositionR(), selectedPosition.getRow());
      int multiplier = (super.getPositionR() > selectedPosition.getRow()) ? 1 : -1;
      for(int i = 1; i < distance; i++){
          if(super.getBoard().getSlotOccupied(selectedPosition.getRow() + (i * multiplier), selectedPosition.getCol()))
            return false;
      }
      System.out.println("move horizontally");
      vertical = false;
      return true;
    }
    return false;
  }
}