/*******************************************************************************
 * Copyright (c) 2008, 2010 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package fr.pacman.soalight;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.cdo.eresource.CDOResource;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.obeonetwork.dsl.environment.Namespace;
import org.obeonetwork.dsl.soa.Component;
import org.obeonetwork.dsl.soa.Service;

import fr.pacman.commons.main.PacmanGenerator_Abs;

/**
 * Entry point of the 'GenerateServerSoa' generation module.
 * 
 * @generated NOT
 */
public class GenerateServerSoaLight extends PacmanGenerator_Abs {
	/**
	 * The name of the module.
	 */
	public static final String c_MODULE_FILE_NAME = "generateServerSoaLight";

	/**
	 * The name of the templates that are to be generated.
	 */
	private final String[] _TEMPLATE_NAMES;

	/**
	 * Allows the public constructor to be used. Note that a generator created this
	 * way cannot be used to launch generations before one of initialize(EObject,
	 * File, List) or initialize(URI, File, List) is called.
	 * <p>
	 * The main reason for this constructor is to allow clients of this generation
	 * to call it from another Java file, as it allows for the retrieval of
	 * {@link #getProperties()} and {@link #getGenerationListeners()}.
	 * </p>
	 */
//   public GenerateServerSoa ()
//   {
//      super();
//   }

	/**
	 * This allows clients to instantiates a generator with all required
	 * information.
	 * 
	 * @param p_model        We'll iterate over the content of this element to find
	 *                       Objects matching the first parameter of the template we
	 *                       need to call.
	 * @param p_targetFolder This will be used as the output folder for this
	 *                       generation : it will be the base path against which all
	 *                       file block URLs will be resolved.
	 * @param p_arguments    If the template which will be called requires more than
	 *                       one argument taken from the model, pass them here.
	 * @throws IOException This can be thrown in two scenarios : the module cannot
	 *                     be found, or it cannot be loaded.
	 */
	public GenerateServerSoaLight(final EObject p_model, final File p_targetFolder,
			final List<? extends Object> p_arguments) throws IOException {
		super(p_model, p_targetFolder, p_arguments);
		if (p_model instanceof CDOResource) {
			_TEMPLATE_NAMES = new String[] { "GenerateServerSystemLight" };
		} else if (p_model instanceof Namespace) {
			_TEMPLATE_NAMES = new String[] { "GenerateServerSystemLight" };
		} else if (p_model instanceof Component) {
			_TEMPLATE_NAMES = new String[] { "GenerateServerComponentLight" };
		} else if (p_model instanceof Service) {
			_TEMPLATE_NAMES = new String[] { "GenerateServerServiceLight" };
		} else {
			// Si type non trouvé, il faut partir de la racine

			_TEMPLATE_NAMES = new String[] { "GenerateServerSystemLight" };
			// throw new IllegalStateException("Type d'objet non géré : " +
			// p_model.getClass().getName());
		}
	}

	/**
	 * This allows clients to instantiates a generator with all required
	 * information.
	 * 
	 * @param p_modelURI     URI where the model on which this generator will be
	 *                       used is located.
	 * @param p_targetFolder This will be used as the output folder for this
	 *                       generation : it will be the base path against which all
	 *                       file block URLs will be resolved.
	 * @param p_arguments    If the template which will be called requires more than
	 *                       one argument taken from the model, pass them here.
	 * @throws IOException This can be thrown in three scenarios : the module cannot
	 *                     be found, it cannot be loaded, or the model cannot be
	 *                     loaded.
	 */
	public GenerateServerSoaLight(final URI p_modelURI, final File p_targetFolder,
			final List<? extends Object> p_arguments) throws IOException {
		super(p_modelURI, p_targetFolder, p_arguments);
		_TEMPLATE_NAMES = new String[] { "GenerateServerSystemLight" };
	}

	/**
	 * This can be used to launch the generation from a standalone application.
	 * 
	 * @param p_args Arguments of the generation.
	 */
	public static void main(final String[] p_args) {
		try {
			if (p_args.length < 2) {
				throw new IllegalArgumentException("Arguments not valid : {model, folder}.");
			} else {
				final URI v_modelURI = URI.createFileURI(p_args[0]);
				final File v_folder = new File(p_args[1]);
				final List<String> v_arguments = new ArrayList<String>();
				for (int v_i = 2; v_i < p_args.length; v_i++) {
					v_arguments.add(p_args[v_i]);
				}
				final GenerateServerSoaLight v_generator = new GenerateServerSoaLight(v_modelURI, v_folder,
						v_arguments);
				v_generator.doGenerate(new BasicMonitor());
			}
		} catch (final IOException v_e) {
			v_e.printStackTrace(); 
		}
	}

	@Override
	public String[] getModuleTemplates() {
		return _TEMPLATE_NAMES;
	}

	@Override
	public String getModuleFileName() {
		return c_MODULE_FILE_NAME;
	}

	@Override
	public String getProjectName() {
		return Activator.c_PLUGIN_ID;
	}

	@Override
	public boolean getSwitchQueryCache() {
		return Boolean.TRUE;
	}}
