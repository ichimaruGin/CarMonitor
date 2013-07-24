package org.zju.carmonitor.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tree.TreeGrid;

/**
 * @author jiezhen 7/23/13
 */
public class CategoryTreeGrid extends TreeGrid {

    public CategoryTreeGrid(DataSource supplyCategoryDS) {

        setShowHeader(false);
        setLeaveScrollbarGap(false);
        setAnimateFolders(true);
        setCanAcceptDroppedRecords(true);
        setCanReparentNodes(false);
        setSelectionType(SelectionStyle.SINGLE);
        setDataSource(supplyCategoryDS);
    }
}