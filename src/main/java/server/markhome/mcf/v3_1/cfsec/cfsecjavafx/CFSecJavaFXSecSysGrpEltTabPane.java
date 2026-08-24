// Description: Java 25 JavaFX Element TabPane implementation for SecSysGrp.

/*
 *	server.markhome.mcf.CFSec
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *	
 *	Mark's Code Fractal 3.1 CFSec - Security Services
 *	
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow mark.sobkow@gmail.com
 *	
 *	These files are part of Mark's Code Fractal CFSec.
 *	
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *	
 *	http://www.apache.org/licenses/LICENSE-2.0
 *	
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 *	
 */

package server.markhome.mcf.v3_1.cfsec.cfsecjavafx;

import java.math.*;
import java.time.*;
import java.text.*;
import java.util.*;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

/**
 *	CFSecJavaFXSecSysGrpEltTabPane JavaFX Element TabPane implementation
 *	for SecSysGrp.
 */
public class CFSecJavaFXSecSysGrpEltTabPane
extends CFTabPane
implements ICFSecJavaFXSecSysGrpPaneCommon
{
	protected ICFFormManager cfFormManager = null;
	protected ICFSecJavaFXSchema javafxSchema = null;
	protected boolean javafxIsInitializing = true;
	public final String LABEL_TabComponentsImplSysRoleAttr = "Optional Components Implements role";
	protected CFTab tabComponentsImplSysRole = null;
	public final String LABEL_TabComponentsIncByGrpList = "Optional Components Included by Group";
	protected CFTab tabComponentsIncByGrp = null;
	public final String LABEL_TabChildrenMembByGrpList = "Optional Children Members of Group";
	protected CFTab tabChildrenMembByGrp = null;
	public final String LABEL_TabComponentsImplClusGrpList = "Optional Components Implements cluster group";
	protected CFTab tabComponentsImplClusGrp = null;
	public final String LABEL_TabComponentsImplTentGrpList = "Optional Components Implements tenant group";
	protected CFTab tabComponentsImplTentGrp = null;
	public final String LABEL_TabComponentsImplClusRoleList = "Optional Components Implements cluster role";
	protected CFTab tabComponentsImplClusRole = null;
	public final String LABEL_TabComponentsImplTentRoleList = "Optional Components Implements tenant role";
	protected CFTab tabComponentsImplTentRole = null;
	public final String LABEL_TabChildrenSysGrpByNameList = "Optional Children SysGroup by Name";
	protected CFTab tabChildrenSysGrpByName = null;
	public final String LABEL_TabChildrenRoleByEnableNameList = "Optional Children System Role by Name";
	protected CFTab tabChildrenRoleByEnableName = null;
	protected ScrollPane tabViewComponentsImplSysRoleAttrScrollPane = null;
	protected CFGridPane tabViewComponentsImplSysRoleAttrPane = null;
	protected CFBorderPane tabViewComponentsIncByGrpListPane = null;
	protected CFBorderPane tabViewChildrenMembByGrpListPane = null;
	protected CFBorderPane tabViewComponentsImplClusGrpListPane = null;
	protected CFBorderPane tabViewComponentsImplTentGrpListPane = null;
	protected CFBorderPane tabViewComponentsImplClusRoleListPane = null;
	protected CFBorderPane tabViewComponentsImplTentRoleListPane = null;
	protected CFBorderPane tabViewChildrenSysGrpByNameListPane = null;
	protected CFBorderPane tabViewChildrenRoleByEnableNameListPane = null;

	public CFSecJavaFXSecSysGrpEltTabPane( ICFFormManager formManager, ICFSecJavaFXSchema argSchema, ICFSecSecSysGrpObj argFocus ) {
		super();
		final String S_ProcName = "construct-schema-focus";
		if( formManager == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"formManager" );
		}
		cfFormManager = formManager;
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				2,
				"argSchema" );
		}
		// argFocus is optional; focus may be set later during execution as
		// conditions of the runtime change.
		javafxSchema = argSchema;
		setJavaFXFocusAsSecSysGrp( argFocus );
		// Wire the newly constructed Panes/Tabs to this TabPane
		tabComponentsImplSysRole = new CFTab();
		tabComponentsImplSysRole.setText( LABEL_TabComponentsImplSysRoleAttr );
		tabComponentsImplSysRole.setContent( getTabViewComponentsImplSysRoleAttrScrollPane() );
		getTabs().add( tabComponentsImplSysRole );
		tabComponentsIncByGrp = new CFTab();
		tabComponentsIncByGrp.setText( LABEL_TabComponentsIncByGrpList );
		tabComponentsIncByGrp.setContent( getTabViewComponentsIncByGrpListPane() );
		getTabs().add( tabComponentsIncByGrp );
		tabChildrenMembByGrp = new CFTab();
		tabChildrenMembByGrp.setText( LABEL_TabChildrenMembByGrpList );
		tabChildrenMembByGrp.setContent( getTabViewChildrenMembByGrpListPane() );
		getTabs().add( tabChildrenMembByGrp );
		tabComponentsImplClusGrp = new CFTab();
		tabComponentsImplClusGrp.setText( LABEL_TabComponentsImplClusGrpList );
		tabComponentsImplClusGrp.setContent( getTabViewComponentsImplClusGrpListPane() );
		getTabs().add( tabComponentsImplClusGrp );
		tabComponentsImplTentGrp = new CFTab();
		tabComponentsImplTentGrp.setText( LABEL_TabComponentsImplTentGrpList );
		tabComponentsImplTentGrp.setContent( getTabViewComponentsImplTentGrpListPane() );
		getTabs().add( tabComponentsImplTentGrp );
		tabComponentsImplClusRole = new CFTab();
		tabComponentsImplClusRole.setText( LABEL_TabComponentsImplClusRoleList );
		tabComponentsImplClusRole.setContent( getTabViewComponentsImplClusRoleListPane() );
		getTabs().add( tabComponentsImplClusRole );
		tabComponentsImplTentRole = new CFTab();
		tabComponentsImplTentRole.setText( LABEL_TabComponentsImplTentRoleList );
		tabComponentsImplTentRole.setContent( getTabViewComponentsImplTentRoleListPane() );
		getTabs().add( tabComponentsImplTentRole );
		tabChildrenSysGrpByName = new CFTab();
		tabChildrenSysGrpByName.setText( LABEL_TabChildrenSysGrpByNameList );
		tabChildrenSysGrpByName.setContent( getTabViewChildrenSysGrpByNameListPane() );
		getTabs().add( tabChildrenSysGrpByName );
		tabChildrenRoleByEnableName = new CFTab();
		tabChildrenRoleByEnableName.setText( LABEL_TabChildrenRoleByEnableNameList );
		tabChildrenRoleByEnableName.setContent( getTabViewChildrenRoleByEnableNameListPane() );
		getTabs().add( tabChildrenRoleByEnableName );
		javafxIsInitializing = false;
	}

	public ICFFormManager getCFFormManager() {
		return( cfFormManager );
	}

	public void setCFFormManager( ICFFormManager value ) {
		final String S_ProcName = "setCFFormManager";
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"value" );
		}
		cfFormManager = value;
	}

	public ICFSecJavaFXSchema getJavaFXSchema() {
		return( javafxSchema );
	}

	public void setJavaFXFocus( ICFLibAnyObj value ) {
		final String S_ProcName = "setJavaFXFocus";
		if( ( value == null ) || ( value instanceof ICFSecSecSysGrpObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFSecSecSysGrpObj" );
		}
	}

	public void setJavaFXFocusAsSecSysGrp( ICFSecSecSysGrpObj value ) {
		setJavaFXFocus( value );
	}

	public ICFSecSecSysGrpObj getJavaFXFocusAsSecSysGrp() {
		return( (ICFSecSecSysGrpObj)getJavaFXFocus() );
	}

	public ScrollPane getTabViewComponentsImplSysRoleAttrScrollPane() {
		if( tabViewComponentsImplSysRoleAttrScrollPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			ICFSecSecSysRoleObj refImplSysRole =
				( focus != null )
					? focus.getOptionalComponentsImplSysRole()
					: null;
			tabViewComponentsImplSysRoleAttrPane = javafxSchema.getSecSysRoleFactory().newAttrPane( cfFormManager, refImplSysRole );
			tabViewComponentsImplSysRoleAttrScrollPane = new ScrollPane();
			tabViewComponentsImplSysRoleAttrScrollPane.setFitToWidth( true );
			tabViewComponentsImplSysRoleAttrScrollPane.setHbarPolicy( ScrollBarPolicy.NEVER );
			tabViewComponentsImplSysRoleAttrScrollPane.setVbarPolicy( ScrollBarPolicy.AS_NEEDED );
			tabViewComponentsImplSysRoleAttrScrollPane.setContent( tabViewComponentsImplSysRoleAttrPane );
		}
		return( tabViewComponentsImplSysRoleAttrScrollPane );
	}

	protected class RefreshComponentsIncByGrpList
	implements ICFRefreshCallback
	{
		public RefreshComponentsIncByGrpList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataComponentsIncByGrpList
	implements ICFSecJavaFXSecSysGrpIncPageCallback
	{
		public PageDataComponentsIncByGrpList() {
		}

		public List<ICFSecSecSysGrpIncObj> pageData( $implIJavaOptAtomType$ priorSecSysGrpId,
		$implIJavaOptAtomType$ priorInclName )
		{
			List<ICFSecSecSysGrpIncObj> dataList;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSysGrpIncTableObj().pageSecSysGrpIncBySysGrpIdx( focus.getRequiredSecSysGrpId(),
					priorSecSysGrpId,
					priorInclName );
			}
			else {
				dataList = new ArrayList<ICFSecSecSysGrpIncObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewComponentsIncByGrpListPane() {
		if( tabViewComponentsIncByGrpListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsIncByGrpListPane = javafxSchema.getSecSysGrpIncFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataComponentsIncByGrpList(), new RefreshComponentsIncByGrpList(), false );
		}
		return( tabViewComponentsIncByGrpListPane );
	}

	protected class RefreshChildrenMembByGrpList
	implements ICFRefreshCallback
	{
		public RefreshChildrenMembByGrpList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenMembByGrpList
	implements ICFSecJavaFXSecSysGrpMembPageCallback
	{
		public PageDataChildrenMembByGrpList() {
		}

		public List<ICFSecSecSysGrpMembObj> pageData( $implIJavaOptAtomType$ priorSecSysGrpId,
		$implIJavaOptAtomType$ priorLoginId )
		{
			List<ICFSecSecSysGrpMembObj> dataList;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSysGrpMembTableObj().pageSecSysGrpMembBySysGrpIdx( focus.getRequiredSecSysGrpId(),
					priorSecSysGrpId,
					priorLoginId );
			}
			else {
				dataList = new ArrayList<ICFSecSecSysGrpMembObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenMembByGrpListPane() {
		if( tabViewChildrenMembByGrpListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenMembByGrpListPane = javafxSchema.getSecSysGrpMembFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenMembByGrpList(), new RefreshChildrenMembByGrpList(), false );
		}
		return( tabViewChildrenMembByGrpListPane );
	}

	protected class RefreshComponentsImplClusGrpList
	implements ICFRefreshCallback
	{
		public RefreshComponentsImplClusGrpList() {
		}

		public void refreshMe() {
			Collection<ICFSecSecClusGrpObj> dataCollection;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplClusGrp( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsImplClusGrpListPane();
			ICFSecJavaFXSecClusGrpPaneList jpList = (ICFSecJavaFXSecClusGrpPaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsImplClusGrpListPane() {
		if( tabViewComponentsImplClusGrpListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			Collection<ICFSecSecClusGrpObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplClusGrp( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsImplClusGrpListPane = javafxSchema.getSecClusGrpFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsImplClusGrpList(), false );
		}
		return( tabViewComponentsImplClusGrpListPane );
	}

	protected class RefreshComponentsImplTentGrpList
	implements ICFRefreshCallback
	{
		public RefreshComponentsImplTentGrpList() {
		}

		public void refreshMe() {
			Collection<ICFSecSecTentGrpObj> dataCollection;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplTentGrp( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsImplTentGrpListPane();
			ICFSecJavaFXSecTentGrpPaneList jpList = (ICFSecJavaFXSecTentGrpPaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsImplTentGrpListPane() {
		if( tabViewComponentsImplTentGrpListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			Collection<ICFSecSecTentGrpObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplTentGrp( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsImplTentGrpListPane = javafxSchema.getSecTentGrpFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsImplTentGrpList(), false );
		}
		return( tabViewComponentsImplTentGrpListPane );
	}

	protected class RefreshComponentsImplClusRoleList
	implements ICFRefreshCallback
	{
		public RefreshComponentsImplClusRoleList() {
		}

		public void refreshMe() {
			Collection<ICFSecSecClusRoleObj> dataCollection;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplClusRole( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsImplClusRoleListPane();
			ICFSecJavaFXSecClusRolePaneList jpList = (ICFSecJavaFXSecClusRolePaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsImplClusRoleListPane() {
		if( tabViewComponentsImplClusRoleListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			Collection<ICFSecSecClusRoleObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplClusRole( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsImplClusRoleListPane = javafxSchema.getSecClusRoleFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsImplClusRoleList(), false );
		}
		return( tabViewComponentsImplClusRoleListPane );
	}

	protected class RefreshComponentsImplTentRoleList
	implements ICFRefreshCallback
	{
		public RefreshComponentsImplTentRoleList() {
		}

		public void refreshMe() {
			Collection<ICFSecSecTentRoleObj> dataCollection;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplTentRole( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			CFBorderPane pane = getTabViewComponentsImplTentRoleListPane();
			ICFSecJavaFXSecTentRolePaneList jpList = (ICFSecJavaFXSecTentRolePaneList)pane;
			jpList.setJavaFXDataCollection( dataCollection );
		}
	}

	public CFBorderPane getTabViewComponentsImplTentRoleListPane() {
		if( tabViewComponentsImplTentRoleListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			Collection<ICFSecSecTentRoleObj> dataCollection;
			if( focus != null ) {
				dataCollection = focus.getOptionalComponentsImplTentRole( javafxIsInitializing );
			}
			else {
				dataCollection = null;
			}
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsImplTentRoleListPane = javafxSchema.getSecTentRoleFactory().newListPane( cfFormManager, javafxContainer, null, dataCollection, new RefreshComponentsImplTentRoleList(), false );
		}
		return( tabViewComponentsImplTentRoleListPane );
	}

	protected class RefreshChildrenSysGrpByNameList
	implements ICFRefreshCallback
	{
		public RefreshChildrenSysGrpByNameList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenSysGrpByNameList
	implements ICFSecJavaFXSecSysGrpIncPageCallback
	{
		public PageDataChildrenSysGrpByNameList() {
		}

		public List<ICFSecSecSysGrpIncObj> pageData( $implIJavaOptAtomType$ priorSecSysGrpId,
		$implIJavaOptAtomType$ priorInclName )
		{
			List<ICFSecSecSysGrpIncObj> dataList;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSysGrpIncTableObj().pageSecSysGrpIncByNameIdx( focus.getRequiredName(),
					priorSecSysGrpId,
					priorInclName );
			}
			else {
				dataList = new ArrayList<ICFSecSecSysGrpIncObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenSysGrpByNameListPane() {
		if( tabViewChildrenSysGrpByNameListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenSysGrpByNameListPane = javafxSchema.getSecSysGrpIncFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenSysGrpByNameList(), new RefreshChildrenSysGrpByNameList(), false );
		}
		return( tabViewChildrenSysGrpByNameListPane );
	}

	protected class RefreshChildrenRoleByEnableNameList
	implements ICFRefreshCallback
	{
		public RefreshChildrenRoleByEnableNameList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenRoleByEnableNameList
	implements ICFSecJavaFXSecSysRoleEnablesPageCallback
	{
		public PageDataChildrenRoleByEnableNameList() {
		}

		public List<ICFSecSecSysRoleEnablesObj> pageData( $implIJavaOptAtomType$ priorSecSysRoleId,
		$implIJavaOptAtomType$ priorEnableName )
		{
			List<ICFSecSecSysRoleEnablesObj> dataList;
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSysRoleEnablesTableObj().pageSecSysRoleEnablesByNameIdx( focus.getRequiredName(),
					priorSecSysRoleId,
					priorEnableName );
			}
			else {
				dataList = new ArrayList<ICFSecSecSysRoleEnablesObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenRoleByEnableNameListPane() {
		if( tabViewChildrenRoleByEnableNameListPane == null ) {
			ICFSecSecSysGrpObj focus = (ICFSecSecSysGrpObj)getJavaFXFocusAsSecSysGrp();
			ICFSecSecSysRoleObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysRoleObj ) ) {
				javafxContainer = (ICFSecSecSysRoleObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenRoleByEnableNameListPane = javafxSchema.getSecSysRoleEnablesFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenRoleByEnableNameList(), new RefreshChildrenRoleByEnableNameList(), false );
		}
		return( tabViewChildrenRoleByEnableNameListPane );
	}

	public void setPaneMode( CFPane.PaneMode value ) {
		CFPane.PaneMode oldMode = getPaneMode();
		super.setPaneMode( value );
	if( tabViewComponentsImplSysRoleAttrPane != null ) {
		((ICFSecJavaFXSecSysRolePaneCommon)tabViewComponentsImplSysRoleAttrPane).setPaneMode( CFPane.PaneMode.View );
	}
		if( tabViewComponentsIncByGrpListPane != null ) {
			((ICFSecJavaFXSecSysGrpIncPaneCommon)tabViewComponentsIncByGrpListPane).setPaneMode( value );
		}
		if( tabViewChildrenMembByGrpListPane != null ) {
			((ICFSecJavaFXSecSysGrpMembPaneCommon)tabViewChildrenMembByGrpListPane).setPaneMode( value );
		}
		if( tabViewComponentsImplClusGrpListPane != null ) {
			((ICFSecJavaFXSecClusGrpPaneCommon)tabViewComponentsImplClusGrpListPane).setPaneMode( value );
		}
		if( tabViewComponentsImplTentGrpListPane != null ) {
			((ICFSecJavaFXSecTentGrpPaneCommon)tabViewComponentsImplTentGrpListPane).setPaneMode( value );
		}
		if( tabViewComponentsImplClusRoleListPane != null ) {
			((ICFSecJavaFXSecClusRolePaneCommon)tabViewComponentsImplClusRoleListPane).setPaneMode( value );
		}
		if( tabViewComponentsImplTentRoleListPane != null ) {
			((ICFSecJavaFXSecTentRolePaneCommon)tabViewComponentsImplTentRoleListPane).setPaneMode( value );
		}
		if( tabViewChildrenSysGrpByNameListPane != null ) {
			((ICFSecJavaFXSecSysGrpIncPaneCommon)tabViewChildrenSysGrpByNameListPane).setPaneMode( value );
		}
		if( tabViewChildrenRoleByEnableNameListPane != null ) {
			((ICFSecJavaFXSecSysRoleEnablesPaneCommon)tabViewChildrenRoleByEnableNameListPane).setPaneMode( value );
		}
	}
}
