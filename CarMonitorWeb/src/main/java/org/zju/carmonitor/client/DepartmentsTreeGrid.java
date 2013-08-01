package org.zju.carmonitor.client;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.tree.TreeGrid;

/**
 * @author jiezhen 7/23/13
 */
public class DepartmentsTreeGrid extends TreeGrid {

    public DepartmentsTreeGrid(DataSource departmentDS) {

        setShowHeader(false);
        setLeaveScrollbarGap(false);
        setAnimateFolders(true);
        setCanAcceptDroppedRecords(true);
        setCanReparentNodes(false);
        setSelectionType(SelectionStyle.SINGLE);
        setDataSource(departmentDS);
    }
}