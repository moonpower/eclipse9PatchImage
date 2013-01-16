// Copyright 2012 Jeeeyul Lee, Seoul, Korea
// https://github.com/jeeeyul/pde-tools
//
// This module is multi-licensed and may be used under the terms
// of any of the following licenses:
//
// EPL, Eclipse Public License, V1.0 or later, http://www.eclipse.org/legal
// LGPL, GNU Lesser General Public License, V2.1 or later, http://www.gnu.org/licenses/lgpl.html
// GPL, GNU General Public License, V2 or later, http://www.gnu.org/licenses/gpl.html
// AL, Apache License, V2.0 or later, http://www.apache.org/licenses
// BSD, BSD License, http://www.opensource.org/licenses/bsd-license.php
// MIT, MIT License, http://www.opensource.org/licenses/MIT
//
// Please contact the author if you need another license.
// This module is provided "as is", without warranties of any kind.
package shared;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;

/*
 * Generated by PDE Tools.
 */
public class SharedImages{
	
	/**
	 * <img src="data:image/gif;base64,R0lGODlhEAAQAPcAAAAAAF1wanSSkHSw1n24249SGZ5fHZ5mJ6VsJK1sJK1yK61yL6dxM6tyMKp0Nq12N7R/Mrx/MryFMsOFNsOLNuu5Hea/P/S3MNWyWvC/R/y+RPTEP/3COP/tK//8M+jAW/LJQPnJRf/DU//KWv7JX+7JZeDAePTHZP/PdfjQeP/Qe//wQf/8S/7tZv7tev7/Zv79fIWyvLa3haK7rbu9tpPB3onB4YnC4+fRj+jQiPjQgPjYiPDYkPjYkPjbnvjgmP/gn/jomP78kfjgoPfju/josNfn4dfn4tXr9dnu+fjwyOPq7OPw+fP6/f///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAP8ALAAAAAAQABAAAAioAP8JHEjwn4oRIgoqFIhCyIiFA29g+JABBggLFwZS2EjBBhMSLlq8WNGhgsAETlI6CdCEiA8NLDjIAPJvQpAgPHKYELCk5wkPIQgIlKCkaNEYSJLi2FBigMAIRaJGnWHkiFUaSWoIhDCk65AGDByIFfvAwYJ/CnqoXfCjrdu2Oc4i2LFjAd27d+MKNJBigY6/gP/qFVhgQYrDiA8PHnigsePHECNLjhwQADs=">
	 * Image constant for icons/addscript.gif
	 */
	public static final String ADDSCRIPT = "icons/addscript.gif";
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFIAAAAgCAYAAACBxi9RAAACAUlEQVR4nO3aQUgUURzH8TfzJl13Z2ed0RTTgyGCJOHBQ4fAS+BFOoUXq5NetuzioYOHoK7hSYou6sGNvEhRBmIKsbGeRII6hHiJwsgiK13C3Pz5tjQWZt7s/CFQ6P+Hz2FY5vKdtzMD84QIH7C/yg8AHzWVkU7+z0bTyv+jmh5x+Ff/qGsIDWmb4ka9aeTnayuBxjgLkDse+x0zKURrYEg1nY6KuNacAlpcFuLLyeqDlVnvC+lKY2a0wdlFu1q1rKxMUzVqpHzuC+lIc+3VqRNAZzOL4N3pJiRM42tpSOwf7P462wawyKQhCqLk7eYgJHCugxFYhlEMGfOH7DnDCPQhL3QxAn3Ii92MQB+y/zwj0Ie80ssILFMXcugSI9CHHB5gBPqQt64yAss0NSFvX2cE+pB3bjICfciJEUagDzl1jxHoQz6eZASW1IV89pARWFJqQubmGEFgSCnlTn5xAVjOsQgKSy8g/9wjj4mSksJLOSvZzDjw5iWL4PXMNFK2/d73zca27bvpy33beLsKVt7wYHon5Tj3gz7HViTi8Y2nDzLApw8sRPbJI1TFYpuqWWPgBgE1biIR30gP9G9n52aRX/8IbH1nyo/P61hcmMfQtcGfasF9U63qym1Z8Yp/c8/zVooPIHH420OOBNWi4LruajKZHBP7GwNCQzI6nn84ezQuVTadb48QAAAAAElFTkSuQmCC">
	 * Image constant for icons/drawing-3.9.png
	 */
	public static final String DRAWING_3_9 = "icons/drawing-3.9.png";
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAAeCAYAAAC7Q5mxAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAAN1wAADdcBQiibeAAAABl0RVh0U29mdHdhcmUAd3d3Lmlua3NjYXBlLm9yZ5vuPBoAAAI/SURBVGiB7dq/axNhGAfw73PvmVzeu8TeDdaK6KJQxKG2rrGFCoKdq83i6ujg4Cbqn+AgtBI3FUQkY4UM/gDRQURBKKVVOmidats06aXJ3ePQmITqkObA9w7fD9xy7/Lly/MegSfEzNiLiAYBnAZw4I/D/1MTwGdmXt17QN0FEtGUQ7gPYGBYUN0C6B+GjK06wIsBpwOgusW4yszPfp+1C8wKummDbjz0UnIyLZSFjbM3OyFm1urV9YBnN5mvA60CiWgsZ9CrhWM5OSQM1Tlj7WfIOLGyUVsLeYqZX5gA4Aq6fedQNjNkS9X5Ys8FcPcI5LXVyi0AEyYABKDRCdchWCml4ZJi3BDwv2+MAIAJAJtBePiUm1ObKkGOWoDP7BBR2my9I8NKKw2VNAQCwGS231iWujQJ1lWgnsB+6AmMSE9gRJ0C03oC94UAsJ7AyPQ3MCI9gX3bvcOdAjO2uiwJ1ilQ6gL70VWgozBGcukrHJG+wv1qLTu6JlBf4f3ZbVBPYEQmAAghmjUSptQ/pnsShCFay7jABICDjv31/ZeVk/mzo2qTJcTC0jIcKX+sVyoNEwB2grD8+Hn5eD5/Ti9FevBovtyEYbwEOmtNz5Zy+UlxbuDi+UnV+WLt9dt3uDA9s7Xt+8PM/K29WCeicduWpSuFgixcmk6NjZyBlBnFcePB9318+PgJT0ulxmzxwXa1VrvMzPMAAGZuPwA8x3HueZ63KIRoAGD9gIUQTdd1l7LZbBHAYHdn9Lc/F2m9+wUktPrgKWkYhwAAAABJRU5ErkJggg==">
	 * Image constant for icons/drawing-3.png
	 */
	public static final String DRAWING_3 = "icons/drawing-3.png";
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAAeCAYAAAC7Q5mxAAABjklEQVR42u3ZMYrCQBTG8SQoRIhaBQtBJIhYCIoYxEKwtLQVm9imtFOLsddjxHN4DcFTBEybb31sIAOrZlzYZdm8B78DzB+S6BtNyx78Mh4eHp6/MsU7986/2+eMn5y9+N14A02zrrruhIbhRYYhkC9eRGenBp8t3o5XuplmEJfLQJ5RA2rxTsSirlvXSiWIbRtgALWgJg8eZ2gPfnu5hYITNhoAS1GT5J2YGdCvVr2o3QZYipokH5bMgPtaTaDXA5NQk+TrnB2wXhcYjcAk1EQ5YLMpMJ2CSaiJcsBWS2A2A5NQE+WAnY7AfA4moSbKAbtdgcUCTEJNlAP2+wKrFZiEmigHdF0B3weTUBPlgOOxwHoNJqEmygEnE4HdDkxCTVQD+sOhFx2PAEtRE9W/cq5tO+HpBLAUNXm2TPiyziqVrOt2G8TnM8AAakFNnq2zHi5UTbN0OxyC+HIB8owaUIsnC9WX17ADy7KuzaYTLpdetNkI5Amdmc5ODV5so8GXSj94qcTDw/MvBjn3cj4AHwfka4DirG8AAAAASUVORK5CYII=">
	 * Image constant for icons/sample_button.9.png
	 */
	public static final String SAMPLE_BUTTON_9 = "icons/sample_button.9.png";
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAAeCAYAAAC7Q5mxAAABr0lEQVR4nO2au4rCQBSGRzGQQNRKLAQJQcRCUMQgFoKlpa3YxDalnVpMen2M+By+hpCnEEzrvx7cXQeNa3Rhi8354QvkVpyPuZCZCJEsUIg7Fz/cj7se98yj9+Ke43A4nL+IdsY5453xU4b3Wbsm3kxHCDPMZOxDNutG2axEunAjqp0cXFy8LM846npwyueBNEMOyMUrErVMxgwLheBUKgEMQC7Iibjvzvg+KHFyOftQrQLMFXIiLmPiU4FesehG9TrAXCEn4jKx3Am8jV8uS7RaYBTIibjMzk9boF+pSPR6YBTISWKBliUxHIJRICcxAmP7sV+rSYxGYBTIyaMWeDcGNhoS4zEYBXKSuAs3mxKTCRgFcpJYYLstMZuBUSAniQU6joTngVEgJzECY+P3+xLzORgFcpK4BQ4GEqsVGAVyklSg1+260WYDMFfIiXjwKXe3mFAq2YftFmCukBPxYDHhNpphmOFyGZx2O4AByAU5ES+sTnd03Tiu18FpvwfSDDkgF+KdVWnTNEPLsg/TqRstFhJpgmqm2snBO/K+wptKv9hU4nA4/yJp/KEncc0fu6GesZ/vxv8AAAAASUVORK5CYII=">
	 * Image constant for icons/sample_button_test.9.png
	 */
	public static final String SAMPLE_BUTTON_TEST_9 = "icons/sample_button_test.9.png";
	
	/**
	 * <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFAAAAAeCAYAAAC7Q5mxAAAABGdBTUEAALGPC/xhBQAAAAlwSFlzAAAN1AAADdQB75G6FQAAABp0RVh0U29mdHdhcmUAUGFpbnQuTkVUIHYzLjUuMTAw9HKhAAACl0lEQVRoQ+3ay2sTURQG8JNO7o3ixqV/iLgpitCufFApQkALARWEItQqKqVChfqgK0vVSB9xZpKW1BTsxrYmmSQUbQtuBDeColVbrc/G2JTqajxjJuZxE4/LIifwQZiEEH58d+6dmQu2bUN1AMDmqAZ1rFCrDBHhNsPLU/YnnPebIg/HQNQoWwnQMnxnU6bMpyPe3OyY9oPjGoxqG+mwN2eFRc4yRUApnHPAMmVbOiLW5sc99sJd4NQwmEMbBMxb5pZmx8w9zf0e6wgosnNRxqPK8yjaYOMofYFkWjXg+uwobHBoAyssfyJeQwVgyhSrSR2ynH8wMMW6O7GVxnHSEF+nBmGFQxs450F3lVAB+OXeTVjk0AZYNhUwYYjP4/3wjEMbuIDO2rTUwLguPkX64AmHNkDANWUIx++Ij8OXYYFDG9QGDIkPwR7IcGgDBPyuNHAmJFf6u2CGQxsk9VqAI/J93zmY5NAGOOHmqhvowQa+6+2AKIc2SOgqIEyH5HJ3O+gc2gBXLGoDp0fk0vkTcJtDG2ADvylDGAHfng7AdQ5tgEs+BRCmhuWb9qNwjUMbYAOz1Q3UsIGvjx2GHg5t4ABm9G07Ki7lEHCxrQUucGgDnERWFUAcwq/8+6CDQxvUBMQGvmxtgpMc2qAI6D4XKdySwYX00v49EODQBklD5jM6bK8AdA4eOQjHmxrBz6lvcCrQ0JnQ5XLx0eafh+pYy4GJAWHt3QWHdu+EFo5q0NwIrZNB8ThuyC4FcD4GW3F6Tt0fkk9vXdJuXDnj7eYUDK52ei8O9oogXu4+TxhyIhYDTQEsHsAH7P647hvC1XbygS4sjrAcC7wHGLRM3wFla0fdDTOFpfbf9qSUf059t/g7/8WmpXKzX2X4u8oKAhKuAAAAAElFTkSuQmCC">
	 * Image constant for icons/test_button.png
	 */
	public static final String TEST_BUTTON = "icons/test_button.png";
	private static final ImageRegistry REGISTRY = new ImageRegistry(Display.getDefault());
	
	public static Image getImage(String key){
		Image result = REGISTRY.get(key);
		if(result == null){
			result = loadImage(key);
			REGISTRY.put(key, result);
		}
		return result;
	}
	
	public static ImageDescriptor getImageDescriptor(String key){
		ImageDescriptor result = REGISTRY.getDescriptor(key);
		if(result == null){
			result = loadImageDescriptor(key);
			REGISTRY.put(key, result);
		}
		return result;
	}
	
	private static Image loadImage(String key) {
		try {
			Bundle bundle = Platform.getBundle("net.moon.util.image9Patch");
			URL resource = null;
			
			if(bundle != null){
				resource = Platform.getBundle("net.moon.util.image9Patch").getResource(key);
			}else{
				resource = new File(key).toURI().toURL();	
			}
			
			Image image = new Image(null, resource.openStream());
			return image;
		} catch (Exception e) {
			e.printStackTrace();
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		}
	}
	
	private static ImageDescriptor loadImageDescriptor(String key) {
		try {
			Bundle bundle = Platform.getBundle("net.moon.util.image9Patch");
			URL resource = null;
			
			if(bundle != null){
				resource = Platform.getBundle("net.moon.util.image9Patch").getResource(key);
			}else{
				resource = new File(key).toURI().toURL();	
			}
			
			ImageDescriptor descriptor = ImageDescriptor.createFromURL(resource);
			return descriptor;
		} catch (Exception e) {
			e.printStackTrace();
			return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJS_ERROR_TSK);
		}
	}
}