package game.tiles.wallTiles;

import game.gfx.Assets;
import game.tiles.Tile;

public class TopLeftWallTile extends Tile{

	public TopLeftWallTile(int id) {
		super(Assets.wall_tiles[0], id);
	}

	@Override
	public boolean isSolid(){
		return true;
	}
}
