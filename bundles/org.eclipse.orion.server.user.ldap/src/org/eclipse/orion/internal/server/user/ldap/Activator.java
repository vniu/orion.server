/*******************************************************************************
 * Copyright (c) 2013 IBM Corporation and others 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.orion.internal.server.user.ldap;

import org.eclipse.orion.server.useradmin.IOrionCredentialsService;
import org.osgi.framework.*;

public class Activator implements BundleActivator {

	public static final String PI_USER_SECURESTORAGE = "org.eclipse.orion.server.user.ldap"; //$NON-NLS-1$
	static BundleContext bundleContext;
	private ServiceRegistration<IOrionCredentialsService> registerService;

	public static BundleContext getContext() {
		return bundleContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext
	 * )
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.bundleContext = bundleContext;
		registerService = bundleContext.registerService(IOrionCredentialsService.class, new LDAPCredentialsService(), null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		if (registerService != null)
			registerService.unregister();
		Activator.bundleContext = null;
	}

}
