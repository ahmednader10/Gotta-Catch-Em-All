package queuingFunctions;

import java.util.ArrayList;

import abstracts.QueuingFunction;
import abstracts.SearchNode;

public class DepthFirst implements QueuingFunction<SearchNode> {

	@Override
	public ArrayList<SearchNode> enqueue(SearchNode N, ArrayList<SearchNode> queue) {
		queue.add(0, N);
		return queue;
	}

}
