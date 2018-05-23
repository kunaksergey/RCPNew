package rcpapp.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.ui.AbstractSourceProvider;
import org.eclipse.ui.ISources;

public class CommandStateProvider extends AbstractSourceProvider{
	public final static String STATE  = "isFolder";
	
	@Override
	public void dispose() {
		
	}

	@Override
	public Map<String, Boolean> getCurrentState() {
		Map<String, Boolean> map = new HashMap<String, Boolean>(1);
		map.put(STATE, Boolean.TRUE);
        return map;
	}

	@Override
	public String[] getProvidedSourceNames() {
		return new String[] {STATE};
	}
	
	    public void setFolder() {
		        fireSourceChanged(ISources.WORKBENCH, STATE, true);
	    }

	    public void setFile() {
    	        fireSourceChanged(ISources.WORKBENCH, STATE, false);
	    }

}
