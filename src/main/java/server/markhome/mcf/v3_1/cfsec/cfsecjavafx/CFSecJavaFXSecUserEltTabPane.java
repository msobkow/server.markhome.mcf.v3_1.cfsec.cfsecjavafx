// Description: Java 25 JavaFX Element TabPane implementation for SecUser.

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
 *	CFSecJavaFXSecUserEltTabPane JavaFX Element TabPane implementation
 *	for SecUser.
 */
public class CFSecJavaFXSecUserEltTabPane
extends CFTabPane
implements ICFSecJavaFXSecUserPaneCommon
{
	protected ICFFormManager cfFormManager = null;
	protected ICFSecJavaFXSchema javafxSchema = null;
	protected boolean javafxIsInitializing = true;
	public final String LABEL_TabComponentsPasswordAttr = "Optional Components Password Singleton";
	protected CFTab tabComponentsPassword = null;
	public final String LABEL_TabComponentsEMConfAttr = "Optional Components EMail Confirmation Singleton";
	protected CFTab tabComponentsEMConf = null;
	public final String LABEL_TabComponentsPWResetAttr = "Optional Components Password Reset Singleton";
	protected CFTab tabComponentsPWReset = null;
	public final String LABEL_TabChildrenPWHistoryAttr = "Optional Children Password History Entries";
	protected CFTab tabChildrenPWHistory = null;
	public final String LABEL_TabComponentsSecSessList = "Optional Components Security Session";
	protected CFTab tabComponentsSecSess = null;
	public final String LABEL_TabChildrenSecProxyList = "Optional Children Security Proxy Session";
	protected CFTab tabChildrenSecProxy = null;
	public final String LABEL_TabChildrenSysSecGrpMembList = "Optional Children System Security Group Members";
	protected CFTab tabChildrenSysSecGrpMemb = null;
	public final String LABEL_TabChildrenClusSecGrpMembList = "Optional Children Cluster Security Group Members";
	protected CFTab tabChildrenClusSecGrpMemb = null;
	public final String LABEL_TabChildrenTentSecGrpMembList = "Optional Children Tenant Security Group Members";
	protected CFTab tabChildrenTentSecGrpMemb = null;
	protected ScrollPane tabViewComponentsPasswordAttrScrollPane = null;
	protected CFGridPane tabViewComponentsPasswordAttrPane = null;
	protected ScrollPane tabViewComponentsEMConfAttrScrollPane = null;
	protected CFGridPane tabViewComponentsEMConfAttrPane = null;
	protected ScrollPane tabViewComponentsPWResetAttrScrollPane = null;
	protected CFGridPane tabViewComponentsPWResetAttrPane = null;
	protected ScrollPane tabViewChildrenPWHistoryAttrScrollPane = null;
	protected CFGridPane tabViewChildrenPWHistoryAttrPane = null;
	protected CFBorderPane tabViewComponentsSecSessListPane = null;
	protected CFBorderPane tabViewChildrenSecProxyListPane = null;
	protected CFBorderPane tabViewChildrenSysSecGrpMembListPane = null;
	protected CFBorderPane tabViewChildrenClusSecGrpMembListPane = null;
	protected CFBorderPane tabViewChildrenTentSecGrpMembListPane = null;

	public CFSecJavaFXSecUserEltTabPane( ICFFormManager formManager, ICFSecJavaFXSchema argSchema, ICFSecSecUserObj argFocus ) {
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
		setJavaFXFocusAsSecUser( argFocus );
		// Wire the newly constructed Panes/Tabs to this TabPane
		tabComponentsPassword = new CFTab();
		tabComponentsPassword.setText( LABEL_TabComponentsPasswordAttr );
		tabComponentsPassword.setContent( getTabViewComponentsPasswordAttrScrollPane() );
		getTabs().add( tabComponentsPassword );
		tabComponentsEMConf = new CFTab();
		tabComponentsEMConf.setText( LABEL_TabComponentsEMConfAttr );
		tabComponentsEMConf.setContent( getTabViewComponentsEMConfAttrScrollPane() );
		getTabs().add( tabComponentsEMConf );
		tabComponentsPWReset = new CFTab();
		tabComponentsPWReset.setText( LABEL_TabComponentsPWResetAttr );
		tabComponentsPWReset.setContent( getTabViewComponentsPWResetAttrScrollPane() );
		getTabs().add( tabComponentsPWReset );
		tabChildrenPWHistory = new CFTab();
		tabChildrenPWHistory.setText( LABEL_TabChildrenPWHistoryAttr );
		tabChildrenPWHistory.setContent( getTabViewChildrenPWHistoryAttrScrollPane() );
		getTabs().add( tabChildrenPWHistory );
		tabComponentsSecSess = new CFTab();
		tabComponentsSecSess.setText( LABEL_TabComponentsSecSessList );
		tabComponentsSecSess.setContent( getTabViewComponentsSecSessListPane() );
		getTabs().add( tabComponentsSecSess );
		tabChildrenSecProxy = new CFTab();
		tabChildrenSecProxy.setText( LABEL_TabChildrenSecProxyList );
		tabChildrenSecProxy.setContent( getTabViewChildrenSecProxyListPane() );
		getTabs().add( tabChildrenSecProxy );
		tabChildrenSysSecGrpMemb = new CFTab();
		tabChildrenSysSecGrpMemb.setText( LABEL_TabChildrenSysSecGrpMembList );
		tabChildrenSysSecGrpMemb.setContent( getTabViewChildrenSysSecGrpMembListPane() );
		getTabs().add( tabChildrenSysSecGrpMemb );
		tabChildrenClusSecGrpMemb = new CFTab();
		tabChildrenClusSecGrpMemb.setText( LABEL_TabChildrenClusSecGrpMembList );
		tabChildrenClusSecGrpMemb.setContent( getTabViewChildrenClusSecGrpMembListPane() );
		getTabs().add( tabChildrenClusSecGrpMemb );
		tabChildrenTentSecGrpMemb = new CFTab();
		tabChildrenTentSecGrpMemb.setText( LABEL_TabChildrenTentSecGrpMembList );
		tabChildrenTentSecGrpMemb.setContent( getTabViewChildrenTentSecGrpMembListPane() );
		getTabs().add( tabChildrenTentSecGrpMemb );
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
		if( ( value == null ) || ( value instanceof ICFSecSecUserObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFSecSecUserObj" );
		}
	}

	public void setJavaFXFocusAsSecUser( ICFSecSecUserObj value ) {
		setJavaFXFocus( value );
	}

	public ICFSecSecUserObj getJavaFXFocusAsSecUser() {
		return( (ICFSecSecUserObj)getJavaFXFocus() );
	}

	public ScrollPane getTabViewComponentsPasswordAttrScrollPane() {
		if( tabViewComponentsPasswordAttrScrollPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecUserPasswordObj refPassword =
				( focus != null )
					? focus.getOptionalComponentsPassword()
					: null;
			tabViewComponentsPasswordAttrPane = javafxSchema.getSecUserPasswordFactory().newAttrPane( cfFormManager, refPassword );
			tabViewComponentsPasswordAttrScrollPane = new ScrollPane();
			tabViewComponentsPasswordAttrScrollPane.setFitToWidth( true );
			tabViewComponentsPasswordAttrScrollPane.setHbarPolicy( ScrollBarPolicy.NEVER );
			tabViewComponentsPasswordAttrScrollPane.setVbarPolicy( ScrollBarPolicy.AS_NEEDED );
			tabViewComponentsPasswordAttrScrollPane.setContent( tabViewComponentsPasswordAttrPane );
		}
		return( tabViewComponentsPasswordAttrScrollPane );
	}

	public ScrollPane getTabViewComponentsEMConfAttrScrollPane() {
		if( tabViewComponentsEMConfAttrScrollPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecUserEMConfObj refEMConf =
				( focus != null )
					? focus.getOptionalComponentsEMConf()
					: null;
			tabViewComponentsEMConfAttrPane = javafxSchema.getSecUserEMConfFactory().newAttrPane( cfFormManager, refEMConf );
			tabViewComponentsEMConfAttrScrollPane = new ScrollPane();
			tabViewComponentsEMConfAttrScrollPane.setFitToWidth( true );
			tabViewComponentsEMConfAttrScrollPane.setHbarPolicy( ScrollBarPolicy.NEVER );
			tabViewComponentsEMConfAttrScrollPane.setVbarPolicy( ScrollBarPolicy.AS_NEEDED );
			tabViewComponentsEMConfAttrScrollPane.setContent( tabViewComponentsEMConfAttrPane );
		}
		return( tabViewComponentsEMConfAttrScrollPane );
	}

	public ScrollPane getTabViewComponentsPWResetAttrScrollPane() {
		if( tabViewComponentsPWResetAttrScrollPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecUserPWResetObj refPWReset =
				( focus != null )
					? focus.getOptionalComponentsPWReset()
					: null;
			tabViewComponentsPWResetAttrPane = javafxSchema.getSecUserPWResetFactory().newAttrPane( cfFormManager, refPWReset );
			tabViewComponentsPWResetAttrScrollPane = new ScrollPane();
			tabViewComponentsPWResetAttrScrollPane.setFitToWidth( true );
			tabViewComponentsPWResetAttrScrollPane.setHbarPolicy( ScrollBarPolicy.NEVER );
			tabViewComponentsPWResetAttrScrollPane.setVbarPolicy( ScrollBarPolicy.AS_NEEDED );
			tabViewComponentsPWResetAttrScrollPane.setContent( tabViewComponentsPWResetAttrPane );
		}
		return( tabViewComponentsPWResetAttrScrollPane );
	}

	public ScrollPane getTabViewChildrenPWHistoryAttrScrollPane() {
		if( tabViewChildrenPWHistoryAttrScrollPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecUserPWHistoryObj refPWHistory =
				( focus != null )
					? focus.getOptionalChildrenPWHistory()
					: null;
			tabViewChildrenPWHistoryAttrPane = javafxSchema.getSecUserPWHistoryFactory().newAttrPane( cfFormManager, refPWHistory );
			tabViewChildrenPWHistoryAttrScrollPane = new ScrollPane();
			tabViewChildrenPWHistoryAttrScrollPane.setFitToWidth( true );
			tabViewChildrenPWHistoryAttrScrollPane.setHbarPolicy( ScrollBarPolicy.NEVER );
			tabViewChildrenPWHistoryAttrScrollPane.setVbarPolicy( ScrollBarPolicy.AS_NEEDED );
			tabViewChildrenPWHistoryAttrScrollPane.setContent( tabViewChildrenPWHistoryAttrPane );
		}
		return( tabViewChildrenPWHistoryAttrScrollPane );
	}

	protected class RefreshComponentsSecSessList
	implements ICFRefreshCallback
	{
		public RefreshComponentsSecSessList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataComponentsSecSessList
	implements ICFSecJavaFXSecSessionPageCallback
	{
		public PageDataComponentsSecSessList() {
		}

		public List<ICFSecSecSessionObj> pageData( $implIJavaOptAtomType$ priorSecSessionId )
		{
			List<ICFSecSecSessionObj> dataList;
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSessionTableObj().pageSecSessionBySecUserIdx( focus.getRequiredSecUserId(),
					priorSecSessionId );
			}
			else {
				dataList = new ArrayList<ICFSecSecSessionObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewComponentsSecSessListPane() {
		if( tabViewComponentsSecSessListPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecUserObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecUserObj ) ) {
				javafxContainer = (ICFSecSecUserObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewComponentsSecSessListPane = javafxSchema.getSecSessionFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataComponentsSecSessList(), new RefreshComponentsSecSessList(), false );
		}
		return( tabViewComponentsSecSessListPane );
	}

	protected class RefreshChildrenSecProxyList
	implements ICFRefreshCallback
	{
		public RefreshChildrenSecProxyList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenSecProxyList
	implements ICFSecJavaFXSecSessionPageCallback
	{
		public PageDataChildrenSecProxyList() {
		}

		public List<ICFSecSecSessionObj> pageData( $implIJavaOptAtomType$ priorSecSessionId )
		{
			List<ICFSecSecSessionObj> dataList;
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSessionTableObj().pageSecSessionBySecProxyIdx( focus.getRequiredSecUserId(),
					priorSecSessionId );
			}
			else {
				dataList = new ArrayList<ICFSecSecSessionObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenSecProxyListPane() {
		if( tabViewChildrenSecProxyListPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecUserObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecUserObj ) ) {
				javafxContainer = (ICFSecSecUserObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenSecProxyListPane = javafxSchema.getSecSessionFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenSecProxyList(), new RefreshChildrenSecProxyList(), false );
		}
		return( tabViewChildrenSecProxyListPane );
	}

	protected class RefreshChildrenSysSecGrpMembList
	implements ICFRefreshCallback
	{
		public RefreshChildrenSysSecGrpMembList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenSysSecGrpMembList
	implements ICFSecJavaFXSecSysGrpMembPageCallback
	{
		public PageDataChildrenSysSecGrpMembList() {
		}

		public List<ICFSecSecSysGrpMembObj> pageData( $implIJavaOptAtomType$ priorSecSysGrpId,
		$implIJavaOptAtomType$ priorLoginId )
		{
			List<ICFSecSecSysGrpMembObj> dataList;
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecSysGrpMembTableObj().pageSecSysGrpMembByLoginIdx( focus.getRequiredLoginId(),
					priorSecSysGrpId,
					priorLoginId );
			}
			else {
				dataList = new ArrayList<ICFSecSecSysGrpMembObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenSysSecGrpMembListPane() {
		if( tabViewChildrenSysSecGrpMembListPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecSysGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecSysGrpObj ) ) {
				javafxContainer = (ICFSecSecSysGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenSysSecGrpMembListPane = javafxSchema.getSecSysGrpMembFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenSysSecGrpMembList(), new RefreshChildrenSysSecGrpMembList(), false );
		}
		return( tabViewChildrenSysSecGrpMembListPane );
	}

	protected class RefreshChildrenClusSecGrpMembList
	implements ICFRefreshCallback
	{
		public RefreshChildrenClusSecGrpMembList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenClusSecGrpMembList
	implements ICFSecJavaFXSecClusGrpMembPageCallback
	{
		public PageDataChildrenClusSecGrpMembList() {
		}

		public List<ICFSecSecClusGrpMembObj> pageData( $implIJavaOptAtomType$ priorSecClusGrpId,
		$implIJavaOptAtomType$ priorLoginId )
		{
			List<ICFSecSecClusGrpMembObj> dataList;
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecClusGrpMembTableObj().pageSecClusGrpMembByLoginIdx( focus.getRequiredLoginId(),
					priorSecClusGrpId,
					priorLoginId );
			}
			else {
				dataList = new ArrayList<ICFSecSecClusGrpMembObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenClusSecGrpMembListPane() {
		if( tabViewChildrenClusSecGrpMembListPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecClusGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecClusGrpObj ) ) {
				javafxContainer = (ICFSecSecClusGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenClusSecGrpMembListPane = javafxSchema.getSecClusGrpMembFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenClusSecGrpMembList(), new RefreshChildrenClusSecGrpMembList(), false );
		}
		return( tabViewChildrenClusSecGrpMembListPane );
	}

	protected class RefreshChildrenTentSecGrpMembList
	implements ICFRefreshCallback
	{
		public RefreshChildrenTentSecGrpMembList() {
		}

		public void refreshMe() {
			// Use page data instead
		}
	}

	protected class PageDataChildrenTentSecGrpMembList
	implements ICFSecJavaFXSecTentGrpMembPageCallback
	{
		public PageDataChildrenTentSecGrpMembList() {
		}

		public List<ICFSecSecTentGrpMembObj> pageData( $implIJavaOptAtomType$ priorSecTentGrpId,
		$implIJavaOptAtomType$ priorLoginId )
		{
			List<ICFSecSecTentGrpMembObj> dataList;
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			if( focus != null ) {
				ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
				dataList = schemaObj.getSecTentGrpMembTableObj().pageSecTentGrpMembByUserIdx( focus.getRequiredLoginId(),
					priorSecTentGrpId,
					priorLoginId );
			}
			else {
				dataList = new ArrayList<ICFSecSecTentGrpMembObj>();
			}
			return( dataList );
		}
	}

	public CFBorderPane getTabViewChildrenTentSecGrpMembListPane() {
		if( tabViewChildrenTentSecGrpMembListPane == null ) {
			ICFSecSecUserObj focus = (ICFSecSecUserObj)getJavaFXFocusAsSecUser();
			ICFSecSecTentGrpObj javafxContainer;
			if( ( focus != null ) && ( focus instanceof ICFSecSecTentGrpObj ) ) {
				javafxContainer = (ICFSecSecTentGrpObj)focus;
			}
			else {
				javafxContainer = null;
			}
			tabViewChildrenTentSecGrpMembListPane = javafxSchema.getSecTentGrpMembFactory().newListPane( cfFormManager, javafxContainer, null, new PageDataChildrenTentSecGrpMembList(), new RefreshChildrenTentSecGrpMembList(), false );
		}
		return( tabViewChildrenTentSecGrpMembListPane );
	}

	public void setPaneMode( CFPane.PaneMode value ) {
		CFPane.PaneMode oldMode = getPaneMode();
		super.setPaneMode( value );
	if( tabViewComponentsPasswordAttrPane != null ) {
		((ICFSecJavaFXSecUserPasswordPaneCommon)tabViewComponentsPasswordAttrPane).setPaneMode( CFPane.PaneMode.View );
	}
	if( tabViewComponentsEMConfAttrPane != null ) {
		((ICFSecJavaFXSecUserEMConfPaneCommon)tabViewComponentsEMConfAttrPane).setPaneMode( CFPane.PaneMode.View );
	}
	if( tabViewComponentsPWResetAttrPane != null ) {
		((ICFSecJavaFXSecUserPWResetPaneCommon)tabViewComponentsPWResetAttrPane).setPaneMode( CFPane.PaneMode.View );
	}
	if( tabViewChildrenPWHistoryAttrPane != null ) {
		((ICFSecJavaFXSecUserPWHistoryPaneCommon)tabViewChildrenPWHistoryAttrPane).setPaneMode( CFPane.PaneMode.View );
	}
		if( tabViewComponentsSecSessListPane != null ) {
			((ICFSecJavaFXSecSessionPaneCommon)tabViewComponentsSecSessListPane).setPaneMode( value );
		}
		if( tabViewChildrenSecProxyListPane != null ) {
			((ICFSecJavaFXSecSessionPaneCommon)tabViewChildrenSecProxyListPane).setPaneMode( value );
		}
		if( tabViewChildrenSysSecGrpMembListPane != null ) {
			((ICFSecJavaFXSecSysGrpMembPaneCommon)tabViewChildrenSysSecGrpMembListPane).setPaneMode( value );
		}
		if( tabViewChildrenClusSecGrpMembListPane != null ) {
			((ICFSecJavaFXSecClusGrpMembPaneCommon)tabViewChildrenClusSecGrpMembListPane).setPaneMode( value );
		}
		if( tabViewChildrenTentSecGrpMembListPane != null ) {
			((ICFSecJavaFXSecTentGrpMembPaneCommon)tabViewChildrenTentSecGrpMembListPane).setPaneMode( value );
		}
	}
}
