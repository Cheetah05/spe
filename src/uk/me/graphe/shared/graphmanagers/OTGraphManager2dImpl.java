package uk.me.graphe.shared.graphmanagers;

import java.util.ArrayList;
import java.util.List;

import uk.me.graphe.client.Graphemeui;
import uk.me.graphe.shared.Edge;
import uk.me.graphe.shared.messages.operations.AddNodeOperation;
import uk.me.graphe.shared.messages.operations.CompositeOperation;
import uk.me.graphe.shared.messages.operations.EdgeOperation;
import uk.me.graphe.shared.messages.operations.GraphOperation;
import uk.me.graphe.shared.messages.operations.MoveNodeOperation;
import uk.me.graphe.shared.messages.operations.NodeOperation;

public class OTGraphManager2dImpl extends GraphManager2dImpl implements
        OTGraphManager2d {

    private int mId;
    private int mStateId;
    private List<GraphOperation> mHistory = new ArrayList<GraphOperation>();

    protected OTGraphManager2dImpl(int id) {
        super();
        mId = id;
        mStateId = 0;
    }

    @Override
    public void applyOperation(GraphOperation graphOperation) {
        mStateId++;
        mHistory.add(graphOperation);
        graphOperation.applyTo(this);
    }

    @Override
    public int getGraphId() {
        return mId;
    }

    @Override
    public CompositeOperation getOperationDelta(int historyId) {
        return new CompositeOperation(mHistory.subList(historyId, mHistory.size()));
    }

    @Override
    public int getStateId() {
        return mStateId;
    }

    public void setStateId(int m) {
        mStateId = m;
    }

    @Override
    public CompositeOperation getCompleteHistory() {
        return this.getOperationDelta(0);
    }

}
