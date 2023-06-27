package smartclubs.gui.impl;

import smartclubs.group.management.Group;
import smartclubs.group.management.GroupData;
import smartclubs.gui.PluginInventory;
import smartclubs.profile.PlayerProfile;

public class GroupConfigurationInventory extends PluginInventory {
    private GroupData accessibleGroupData;
    public GroupConfigurationInventory(Group groupToManage, PlayerProfile user) {
        super(new GroupData(groupToManage).groupName, user, true, 36);
        this.accessibleGroupData = new GroupData(groupToManage);
    }

}
