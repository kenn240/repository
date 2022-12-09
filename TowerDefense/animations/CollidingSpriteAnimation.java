
public class CollidingSpriteAnimation implements Animation {

	private int universeCount = 0;
	private Universe current = null;
	
	public Universe getNextUniverse() {

		
			this.current = new SkeletonDefenseUniverse();
		
		return this.current;

	}

	public Universe getCurrentUniverse() {
		return this.current;
	}

	public void restart() {
		universeCount = 1;
		current = null;
		
	}
	
}
