package tree.implementation;

import resource.DBNode;
import resource.DBNodeComposite;
import resource.implementation.InformationResource;
import tree.Tree;
import tree.TreeItem;

import javax.swing.tree.DefaultTreeModel;
import java.util.List;

public class TreeImplementation implements Tree {

    private TreeItem root;

    @Override
    public DefaultTreeModel generateTree(InformationResource informationResource) {

        root = new TreeItem(informationResource, informationResource.getName());
        connectChildren(root);
        return new DefaultTreeModel(root);
    }


    private void connectChildren(TreeItem current){

        if (!(current.getDbNode() instanceof DBNodeComposite)) return;

        List<DBNode> children = ((DBNodeComposite) current.getDbNode()).getChildren();
        for (int i = 0; i<children.size();i++){
            TreeItem child = new TreeItem(children.get(i), children.get(i).getName());
            current.insert(child,i);
            connectChildren(child);
        }

    }

    public TreeItem getRoot(){
        return root;
    }

}
