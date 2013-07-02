package org.jkiss.dbeaver.core.eclipse.search;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.search.ui.ISearchPage;
import org.eclipse.search.ui.ISearchPageContainer;
import org.eclipse.search.ui.NewSearchUI;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.jkiss.dbeaver.ui.UIUtils;
import org.jkiss.dbeaver.ui.search.IObjectSearchContainer;
import org.jkiss.dbeaver.ui.search.IObjectSearchPage;

/**
 * Search page adapter
 */
public class SearchPageAdapter implements ISearchPage {

    private final IObjectSearchPage source;

    public SearchPageAdapter(IObjectSearchPage source)
    {
        this.source = source;
    }


    @Override
    public boolean performAction()
    {
        try {
            NewSearchUI.runQueryInBackground(new SearchQueryAdapter(source.createQuery()));
        } catch (Exception e) {
            UIUtils.showErrorDialog(getControl().getShell(),
                "Search",
                "Can't perform search",
                e);
            return false;
        }
        return true;
    }

    @Override
    public void setContainer(final ISearchPageContainer container)
    {
        source.setSearchContainer(new IObjectSearchContainer() {
            @Override
            public void setSearchEnabled(boolean enabled)
            {
                container.setPerformActionEnabled(enabled);
            }
        });
    }

    @Override
    public void createControl(Composite parent)
    {
        source.createControl(parent);
    }

    @Override
    public void dispose()
    {
        source.dispose();
    }

    @Override
    public Control getControl()
    {
        return source.getControl();
    }

    @Override
    public String getDescription()
    {
        return source.getDescription();
    }

    @Override
    public String getErrorMessage()
    {
        return source.getErrorMessage();
    }

    @Override
    public Image getImage()
    {
        return source.getImage();
    }

    @Override
    public String getMessage()
    {
        return source.getMessage();
    }

    @Override
    public String getTitle()
    {
        return source.getTitle();
    }

    @Override
    public void performHelp()
    {
        source.performHelp();
    }

    @Override
    public void setDescription(String description)
    {
        source.setDescription(description);
    }

    @Override
    public void setImageDescriptor(ImageDescriptor image)
    {
        source.setImageDescriptor(image);
    }

    @Override
    public void setTitle(String title)
    {
        source.setTitle(title);
    }

    @Override
    public void setVisible(boolean visible)
    {
        source.setVisible(visible);
    }
}
