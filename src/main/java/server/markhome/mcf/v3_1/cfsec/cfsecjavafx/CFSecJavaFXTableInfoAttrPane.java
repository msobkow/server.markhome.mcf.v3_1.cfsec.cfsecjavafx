// Description: Java 25 JavaFX Attribute Pane implementation for TableInfo.

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import server.markhome.mcf.v3_1.cflib.javafx.CFReferenceEditor.ICFReferenceCallback;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

/**
 *	CFSecJavaFXTableInfoAttrPane JavaFX Attribute Pane implementation
 *	for TableInfo.
 */
public class CFSecJavaFXTableInfoAttrPane
extends CFGridPane
implements ICFSecJavaFXTableInfoPaneCommon
{
	protected ICFFormManager cfFormManager = null;
	protected ICFSecJavaFXSchema javafxSchema = null;
	boolean javafxIsInitializing = true;

	protected class TableInfoSuperRefCFLabel
		extends CFLabel
	{
		public TableInfoSuperRefCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.ParentSuperRef.EffLabel"));
		}
	}

	protected class CallbackTableInfoSuperRefChosen
	implements ICFSecJavaFXTableInfoChosen
	{
		public CallbackTableInfoSuperRefChosen() {
		}

		public void choseTableInfo( ICFSecTableInfoObj value ) {
			if( javafxReferenceParentSuperRef != null ) {
				ICFSecTableInfoObj cur = getJavaFXFocusAsTableInfo();
				if( cur != null ) {
					ICFSecTableInfoEditObj editObj = (ICFSecTableInfoEditObj)cur.getEdit();
					if( null != editObj ) {
						CFPane.PaneMode curMode = getPaneMode();
						if( ( curMode == CFPane.PaneMode.Add ) || ( curMode == CFPane.PaneMode.Edit ) ) {
							javafxReferenceParentSuperRef.setReferencedObject( value );
							editObj.setOptionalParentSuperRef( value );
						}
					}
				}
			}
		}
	}

	protected class TableInfoSuperRefReferenceCallback
	implements ICFReferenceCallback
	{
		public void chose( ICFLibAnyObj value ) {
			final String S_ProcName = "chose";
			Node cont;
			ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
			ICFSecTableInfoObj focus = getEffJavaFXFocus();
			ICFSecTableInfoObj referencedObj = (ICFSecTableInfoObj)javafxReferenceParentSuperRef.getReferencedObject();
			java.util.List<ICFSecTableInfoObj> listOfTableInfo = null;
			listOfTableInfo = schemaObj.getTableInfoTableObj().readAllTableInfo();
			if( listOfTableInfo == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"listOfTableInfo" );
			}
			Collection<ICFSecTableInfoObj> cltn = listOfTableInfo;
			CFBorderPane form = javafxSchema.getTableInfoFactory().newPickerForm( cfFormManager, referencedObj, null, cltn, new CallbackTableInfoSuperRefChosen() );
			((ICFSecJavaFXTableInfoPaneCommon)form).setPaneMode( CFPane.PaneMode.View );
			cfFormManager.pushForm( form );
		}

		public void view( ICFLibAnyObj value ) {
			final String S_ProcName = "actionPerformed";
			ICFSecTableInfoObj focus = getEffJavaFXFocus();
			if( focus != null ) {
				ICFSecTableInfoObj referencedObj = (ICFSecTableInfoObj)javafxReferenceParentSuperRef.getReferencedObject();
				CFBorderPane form = null;
				if( referencedObj != null ) {
					int classCode = referencedObj.getClassCode();
					ICFSecSchema.ClassMapEntry entry = ICFSecSchema.getClassMapByRuntimeClassCode(classCode);
					int backingClassCode = entry.getBackingClassCode();
					if( entry.getSchemaName().equals("CFSec") && backingClassCode == ICFSecTableInfo.CLASS_CODE ) {
						form = javafxSchema.getTableInfoFactory().newAddForm( cfFormManager, referencedObj, null, true );
						ICFSecJavaFXTableInfoPaneCommon spec = (ICFSecJavaFXTableInfoPaneCommon)form;
						spec.setJavaFXFocus( referencedObj );
						spec.setPaneMode( CFPane.PaneMode.View );
					}
					else {
						throw new CFLibUnsupportedClassException( getClass(),
							S_ProcName,
							"javaFXFocus",
							focus,
							"ICFSecTableInfoObj" );
					}
					cfFormManager.pushForm( form );
				}
			}
		}
	}

	protected class TableInfoSuperRefCFReferenceEditor
		extends CFReferenceEditor
	{
		public TableInfoSuperRefCFReferenceEditor() {
			super( new TableInfoSuperRefReferenceCallback() );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.TableInfoSuperRef.EffLabel" );
		}
	}

	protected class TableInfoIdCFLabel
		extends CFLabel
	{
		public TableInfoIdCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.TableInfoId.EffLabel"));
		}
	}

	protected class TableInfoIdEditor
		extends CFInt32Editor
	{
		public TableInfoIdEditor() {
			super();
			setMinValue( ICFSecTableInfo.TABLEINFOID_MIN_VALUE );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.TableInfoId.EffLabel" );
		}
	}

	protected class SchemaNameCFLabel
		extends CFLabel
	{
		public SchemaNameCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.SchemaName.EffLabel"));
		}
	}

	protected class SchemaNameEditor
		extends CFStringEditor
	{
		public SchemaNameEditor() {
			super();
			setMaxLen( 32 );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.SchemaName.EffLabel" );
		}
	}

	protected class TableNameCFLabel
		extends CFLabel
	{
		public TableNameCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.TableName.EffLabel"));
		}
	}

	protected class TableNameEditor
		extends CFStringEditor
	{
		public TableNameEditor() {
			super();
			setMaxLen( 64 );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.TableName.EffLabel" );
		}
	}

	protected class BackingClassCodeCFLabel
		extends CFLabel
	{
		public BackingClassCodeCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.BackingClassCode.EffLabel"));
		}
	}

	protected class BackingClassCodeEditor
		extends CFInt32Editor
	{
		public BackingClassCodeEditor() {
			super();
			setMinValue( ICFSecTableInfo.BACKINGCLASSCODE_MIN_VALUE );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.BackingClassCode.EffLabel" );
		}
	}

	protected class RuntimeClassCodeCFLabel
		extends CFLabel
	{
		public RuntimeClassCodeCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.RuntimeClassCode.EffLabel"));
		}
	}

	protected class RuntimeClassCodeEditor
		extends CFInt32Editor
	{
		public RuntimeClassCodeEditor() {
			super();
			setMinValue( ICFSecTableInfo.RUNTIMECLASSCODE_MIN_VALUE );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.RuntimeClassCode.EffLabel" );
		}
	}

	protected class HasHistoryCFLabel
		extends CFLabel
	{
		public HasHistoryCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.HasHistory.EffLabel"));
		}
	}

	protected class HasHistoryEditor
		extends CFBoolEditor
	{
		public HasHistoryEditor() {
			super();
			setIsNullable( false );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.HasHistory.EffLabel" );
		}
	}

	protected class IsMutableCFLabel
		extends CFLabel
	{
		public IsMutableCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.IsMutable.EffLabel"));
		}
	}

	protected class IsMutableEditor
		extends CFBoolEditor
	{
		public IsMutableEditor() {
			super();
			setIsNullable( false );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.IsMutable.EffLabel" );
		}
	}

	protected class SecScopeNameCFLabel
		extends CFLabel
	{
		public SecScopeNameCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.SecScopeName.EffLabel"));
		}
	}

	protected class SecScopeNameEditor
		extends CFStringEditor
	{
		public SecScopeNameEditor() {
			super();
			setMaxLen( 32 );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.SecScopeName.EffLabel" );
		}
	}

	protected class CodeVisCFLabel
		extends CFLabel
	{
		public CodeVisCFLabel() {
			super();
			setText(Inz.s("cfsec.javafx.TableInfo.AttrPane.CodeVis.EffLabel"));
		}
	}

	protected class CodeVisEditor
		extends CFStringEditor
	{
		public CodeVisEditor() {
			super();
			setMaxLen( 32 );
			setFieldNameInzTag( "cfsec.javafx.TableInfo.AttrPane.CodeVis.EffLabel" );
		}
	}

	protected ICFSecTableInfoObj javafxParentSuperRefObj = null;
	protected TableInfoSuperRefCFLabel javafxLabelParentSuperRef = null;
	protected TableInfoSuperRefCFReferenceEditor javafxReferenceParentSuperRef = null;
	protected TableInfoIdCFLabel javafxLabelTableInfoId = null;
	protected TableInfoIdEditor javafxEditorTableInfoId = null;
	protected SchemaNameCFLabel javafxLabelSchemaName = null;
	protected SchemaNameEditor javafxEditorSchemaName = null;
	protected TableNameCFLabel javafxLabelTableName = null;
	protected TableNameEditor javafxEditorTableName = null;
	protected BackingClassCodeCFLabel javafxLabelBackingClassCode = null;
	protected BackingClassCodeEditor javafxEditorBackingClassCode = null;
	protected RuntimeClassCodeCFLabel javafxLabelRuntimeClassCode = null;
	protected RuntimeClassCodeEditor javafxEditorRuntimeClassCode = null;
	protected HasHistoryCFLabel javafxLabelHasHistory = null;
	protected HasHistoryEditor javafxEditorHasHistory = null;
	protected IsMutableCFLabel javafxLabelIsMutable = null;
	protected IsMutableEditor javafxEditorIsMutable = null;
	protected SecScopeNameCFLabel javafxLabelSecScopeName = null;
	protected SecScopeNameEditor javafxEditorSecScopeName = null;
	protected CodeVisCFLabel javafxLabelCodeVis = null;
	protected CodeVisEditor javafxEditorCodeVis = null;

	public CFSecJavaFXTableInfoAttrPane( ICFFormManager formManager, ICFSecJavaFXSchema argSchema, ICFSecTableInfoObj argFocus ) {
		super();
		Control ctrl;
		CFLabel label;
		CFReferenceEditor reference;
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
		setJavaFXFocusAsTableInfo( argFocus );
		setPadding( new Insets(5) );
		setHgap( 5 );
		setVgap( 5 );
		setAlignment( Pos.CENTER );
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth( 100 );
		getColumnConstraints().addAll( column1 );
		int gridRow = 0;
		label = getJavaFXLabelParentSuperRef();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		reference = getJavaFXReferenceParentSuperRef();
		setHalignment( reference, HPos.LEFT );
		add( reference, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelTableInfoId();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorTableInfoId();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelSchemaName();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorSchemaName();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelTableName();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorTableName();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelBackingClassCode();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorBackingClassCode();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelRuntimeClassCode();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorRuntimeClassCode();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelHasHistory();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorHasHistory();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelIsMutable();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorIsMutable();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelSecScopeName();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorSecScopeName();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		label = getJavaFXLabelCodeVis();
		setHalignment( label, HPos.LEFT );
		setValignment( label, VPos.BOTTOM );
		add( label, 0, gridRow );
		gridRow ++;

		ctrl = getJavaFXEditorCodeVis();
		setHalignment( ctrl, HPos.LEFT );
		add( ctrl, 0, gridRow );
		gridRow ++;

		populateFields();
		adjustComponentEnableStates();
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
		if( ( value == null ) || ( value instanceof ICFSecTableInfoObj ) ) {
			super.setJavaFXFocus( value );
		}
		else {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				"value",
				value,
				"ICFSecTableInfoObj" );
		}
		populateFields();
		adjustComponentEnableStates();
	}

	public ICFSecTableInfoObj getJavaFXFocusAsTableInfo() {
		return( (ICFSecTableInfoObj)getJavaFXFocus() );
	}

	public void setJavaFXFocusAsTableInfo( ICFSecTableInfoObj value ) {
		setJavaFXFocus( value );
	}

	public ICFSecTableInfoObj getEffJavaFXFocus() {
		ICFSecTableInfoObj eff = (ICFSecTableInfoObj)getJavaFXFocus();
		if( eff != null ) {
			if( null != eff.getEdit() ) {
				eff = (ICFSecTableInfoObj)eff.getEdit();
			}
		}
		return( eff );
	}

	public ICFSecTableInfoObj getJavaFXParentSuperRefObj() {
		return( javafxParentSuperRefObj );
	}

	public void setJavaFXParentSuperRefObj( ICFSecTableInfoObj value ) {
		javafxParentSuperRefObj = value;
	}

	public CFLabel getJavaFXLabelParentSuperRef() {
		if( javafxLabelParentSuperRef == null ) {
			javafxLabelParentSuperRef = new TableInfoSuperRefCFLabel();
		}
		return( javafxLabelParentSuperRef );
	}

	public CFReferenceEditor getJavaFXReferenceParentSuperRef() {
		if( javafxReferenceParentSuperRef == null ) {
			javafxReferenceParentSuperRef = new TableInfoSuperRefCFReferenceEditor();
		}
		return( javafxReferenceParentSuperRef );
	}

	public void setJavaFXReferenceParentSuperRef( TableInfoSuperRefCFReferenceEditor value ) {
		javafxReferenceParentSuperRef = value;
	}

	public TableInfoIdCFLabel getJavaFXLabelTableInfoId() {
		if( javafxLabelTableInfoId == null ) {
			javafxLabelTableInfoId = new TableInfoIdCFLabel();
		}
		return( javafxLabelTableInfoId );
	}

	public void setJavaFXLabelTableInfoId( TableInfoIdCFLabel value ) {
		javafxLabelTableInfoId = value;
	}

	public TableInfoIdEditor getJavaFXEditorTableInfoId() {
		if( javafxEditorTableInfoId == null ) {
			javafxEditorTableInfoId = new TableInfoIdEditor();
		}
		return( javafxEditorTableInfoId );
	}

	public void setJavaFXEditorTableInfoId( TableInfoIdEditor value ) {
		javafxEditorTableInfoId = value;
	}

	public SchemaNameCFLabel getJavaFXLabelSchemaName() {
		if( javafxLabelSchemaName == null ) {
			javafxLabelSchemaName = new SchemaNameCFLabel();
		}
		return( javafxLabelSchemaName );
	}

	public void setJavaFXLabelSchemaName( SchemaNameCFLabel value ) {
		javafxLabelSchemaName = value;
	}

	public SchemaNameEditor getJavaFXEditorSchemaName() {
		if( javafxEditorSchemaName == null ) {
			javafxEditorSchemaName = new SchemaNameEditor();
		}
		return( javafxEditorSchemaName );
	}

	public void setJavaFXEditorSchemaName( SchemaNameEditor value ) {
		javafxEditorSchemaName = value;
	}

	public TableNameCFLabel getJavaFXLabelTableName() {
		if( javafxLabelTableName == null ) {
			javafxLabelTableName = new TableNameCFLabel();
		}
		return( javafxLabelTableName );
	}

	public void setJavaFXLabelTableName( TableNameCFLabel value ) {
		javafxLabelTableName = value;
	}

	public TableNameEditor getJavaFXEditorTableName() {
		if( javafxEditorTableName == null ) {
			javafxEditorTableName = new TableNameEditor();
		}
		return( javafxEditorTableName );
	}

	public void setJavaFXEditorTableName( TableNameEditor value ) {
		javafxEditorTableName = value;
	}

	public BackingClassCodeCFLabel getJavaFXLabelBackingClassCode() {
		if( javafxLabelBackingClassCode == null ) {
			javafxLabelBackingClassCode = new BackingClassCodeCFLabel();
		}
		return( javafxLabelBackingClassCode );
	}

	public void setJavaFXLabelBackingClassCode( BackingClassCodeCFLabel value ) {
		javafxLabelBackingClassCode = value;
	}

	public BackingClassCodeEditor getJavaFXEditorBackingClassCode() {
		if( javafxEditorBackingClassCode == null ) {
			javafxEditorBackingClassCode = new BackingClassCodeEditor();
		}
		return( javafxEditorBackingClassCode );
	}

	public void setJavaFXEditorBackingClassCode( BackingClassCodeEditor value ) {
		javafxEditorBackingClassCode = value;
	}

	public RuntimeClassCodeCFLabel getJavaFXLabelRuntimeClassCode() {
		if( javafxLabelRuntimeClassCode == null ) {
			javafxLabelRuntimeClassCode = new RuntimeClassCodeCFLabel();
		}
		return( javafxLabelRuntimeClassCode );
	}

	public void setJavaFXLabelRuntimeClassCode( RuntimeClassCodeCFLabel value ) {
		javafxLabelRuntimeClassCode = value;
	}

	public RuntimeClassCodeEditor getJavaFXEditorRuntimeClassCode() {
		if( javafxEditorRuntimeClassCode == null ) {
			javafxEditorRuntimeClassCode = new RuntimeClassCodeEditor();
		}
		return( javafxEditorRuntimeClassCode );
	}

	public void setJavaFXEditorRuntimeClassCode( RuntimeClassCodeEditor value ) {
		javafxEditorRuntimeClassCode = value;
	}

	public HasHistoryCFLabel getJavaFXLabelHasHistory() {
		if( javafxLabelHasHistory == null ) {
			javafxLabelHasHistory = new HasHistoryCFLabel();
		}
		return( javafxLabelHasHistory );
	}

	public void setJavaFXLabelHasHistory( HasHistoryCFLabel value ) {
		javafxLabelHasHistory = value;
	}

	public HasHistoryEditor getJavaFXEditorHasHistory() {
		if( javafxEditorHasHistory == null ) {
			javafxEditorHasHistory = new HasHistoryEditor();
		}
		return( javafxEditorHasHistory );
	}

	public void setJavaFXEditorHasHistory( HasHistoryEditor value ) {
		javafxEditorHasHistory = value;
	}

	public IsMutableCFLabel getJavaFXLabelIsMutable() {
		if( javafxLabelIsMutable == null ) {
			javafxLabelIsMutable = new IsMutableCFLabel();
		}
		return( javafxLabelIsMutable );
	}

	public void setJavaFXLabelIsMutable( IsMutableCFLabel value ) {
		javafxLabelIsMutable = value;
	}

	public IsMutableEditor getJavaFXEditorIsMutable() {
		if( javafxEditorIsMutable == null ) {
			javafxEditorIsMutable = new IsMutableEditor();
		}
		return( javafxEditorIsMutable );
	}

	public void setJavaFXEditorIsMutable( IsMutableEditor value ) {
		javafxEditorIsMutable = value;
	}

	public SecScopeNameCFLabel getJavaFXLabelSecScopeName() {
		if( javafxLabelSecScopeName == null ) {
			javafxLabelSecScopeName = new SecScopeNameCFLabel();
		}
		return( javafxLabelSecScopeName );
	}

	public void setJavaFXLabelSecScopeName( SecScopeNameCFLabel value ) {
		javafxLabelSecScopeName = value;
	}

	public SecScopeNameEditor getJavaFXEditorSecScopeName() {
		if( javafxEditorSecScopeName == null ) {
			javafxEditorSecScopeName = new SecScopeNameEditor();
		}
		return( javafxEditorSecScopeName );
	}

	public void setJavaFXEditorSecScopeName( SecScopeNameEditor value ) {
		javafxEditorSecScopeName = value;
	}

	public CodeVisCFLabel getJavaFXLabelCodeVis() {
		if( javafxLabelCodeVis == null ) {
			javafxLabelCodeVis = new CodeVisCFLabel();
		}
		return( javafxLabelCodeVis );
	}

	public void setJavaFXLabelCodeVis( CodeVisCFLabel value ) {
		javafxLabelCodeVis = value;
	}

	public CodeVisEditor getJavaFXEditorCodeVis() {
		if( javafxEditorCodeVis == null ) {
			javafxEditorCodeVis = new CodeVisEditor();
		}
		return( javafxEditorCodeVis );
	}

	public void setJavaFXEditorCodeVis( CodeVisEditor value ) {
		javafxEditorCodeVis = value;
	}

	public void populateFields()
	{
		ICFSecTableInfoObj popObj = getEffJavaFXFocus();
		if( getPaneMode() == CFPane.PaneMode.Unknown ) {
			popObj = null;
		}
		if( popObj == null ) {
			javafxParentSuperRefObj = null;
		}
		else {
			javafxParentSuperRefObj = (ICFSecTableInfoObj)popObj.getOptionalParentSuperRef( javafxIsInitializing );
		}
		if( javafxReferenceParentSuperRef != null ) {
			javafxReferenceParentSuperRef.setReferencedObject( javafxParentSuperRefObj );
		}

		if( popObj == null ) {
			getJavaFXEditorTableInfoId().setInt32Value( null );
		}
		else {
			getJavaFXEditorTableInfoId().setInt32Value( popObj.getRequiredTableInfoId() );
		}

		if( popObj == null ) {
			getJavaFXEditorSchemaName().setStringValue( null );
		}
		else {
			getJavaFXEditorSchemaName().setStringValue( popObj.getRequiredSchemaName() );
		}

		if( popObj == null ) {
			getJavaFXEditorTableName().setStringValue( null );
		}
		else {
			getJavaFXEditorTableName().setStringValue( popObj.getRequiredTableName() );
		}

		if( popObj == null ) {
			getJavaFXEditorBackingClassCode().setInt32Value( null );
		}
		else {
			getJavaFXEditorBackingClassCode().setInt32Value( popObj.getRequiredBackingClassCode() );
		}

		if( popObj == null ) {
			getJavaFXEditorRuntimeClassCode().setInt32Value( null );
		}
		else {
			getJavaFXEditorRuntimeClassCode().setInt32Value( popObj.getRequiredRuntimeClassCode() );
		}

		if( popObj == null ) {
			getJavaFXEditorHasHistory().setBooleanValue( null );
		}
		else {
			getJavaFXEditorHasHistory().setBooleanValue( popObj.getRequiredHasHistory() );
		}

		if( popObj == null ) {
			getJavaFXEditorIsMutable().setBooleanValue( null );
		}
		else {
			getJavaFXEditorIsMutable().setBooleanValue( popObj.getRequiredIsMutable() );
		}

		if( popObj == null ) {
			getJavaFXEditorSecScopeName().setStringValue( null );
		}
		else {
			getJavaFXEditorSecScopeName().setStringValue( popObj.getRequiredSecScopeName() );
		}

		if( popObj == null ) {
			getJavaFXEditorCodeVis().setStringValue( null );
		}
		else {
			getJavaFXEditorCodeVis().setStringValue( popObj.getRequiredCodeVis() );
		}
	}

	public void postFields()
	{
		final String S_ProcName = "postFields";
		ICFSecTableInfoObj focus = getJavaFXFocusAsTableInfo();
		ICFSecTableInfoEditObj editObj;
		if( focus != null ) {
			editObj = (ICFSecTableInfoEditObj)(focus.getEdit());
		}
		else {
			editObj = null;
		}
		if( editObj == null ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				Inz.s("cflibjavafx.common.PaneIsUnfocusedOrNotEditing"),
				Inz.x("cflibjavafx.common.PaneIsUnfocusedOrNotEditing") );
		}

		javafxParentSuperRefObj = (ICFSecTableInfoObj)( javafxReferenceParentSuperRef.getReferencedObject() );
		editObj.setOptionalParentSuperRef( javafxParentSuperRefObj );

		if( getJavaFXEditorSchemaName().getStringValue() == null ) {
			editObj.setRequiredSchemaName( "" );
		}
		else {
			editObj.setRequiredSchemaName( getJavaFXEditorSchemaName().getStringValue() );
		}

		if( getJavaFXEditorTableName().getStringValue() == null ) {
			editObj.setRequiredTableName( "" );
		}
		else {
			editObj.setRequiredTableName( getJavaFXEditorTableName().getStringValue() );
		}

		editObj.setRequiredBackingClassCode( getJavaFXEditorBackingClassCode().getInt32Value() );

		editObj.setRequiredRuntimeClassCode( getJavaFXEditorRuntimeClassCode().getInt32Value() );

		editObj.setRequiredHasHistory( getJavaFXEditorHasHistory().getBooleanValue() );

		editObj.setRequiredIsMutable( getJavaFXEditorIsMutable().getBooleanValue() );

		if( getJavaFXEditorSecScopeName().getStringValue() == null ) {
			editObj.setRequiredSecScopeName( "" );
		}
		else {
			editObj.setRequiredSecScopeName( getJavaFXEditorSecScopeName().getStringValue() );
		}

		if( getJavaFXEditorCodeVis().getStringValue() == null ) {
			editObj.setRequiredCodeVis( "" );
		}
		else {
			editObj.setRequiredCodeVis( getJavaFXEditorCodeVis().getStringValue() );
		}
	}

	public void setPaneMode( CFPane.PaneMode value ) {
		final String S_ProcName = "setPaneMode";
		CFPane.PaneMode oldValue = getPaneMode();
		if( oldValue == value ) {
			return;
		}
		ICFSecTableInfoObj focus = getJavaFXFocusAsTableInfo();
		if( ( value != CFPane.PaneMode.Unknown ) && ( value != CFPane.PaneMode.View ) ) {
			if( focus == null ) {
				throw new CFLibNullArgumentException( getClass(),
					S_ProcName,
					0,
					"javaFXFocus" );
			}
		}
		ICFSecTableInfoEditObj editObj;
		if( focus != null ) {
			editObj  = (ICFSecTableInfoEditObj)focus.getEdit();
		}
		else {
			editObj = null;
		}
		switch( value ) {
			case Unknown:
				switch( oldValue ) {
					case Unknown:
						break;
					default:
						if( editObj != null ) {
							editObj.endEdit();
							editObj = null;
						}
						break;
				}
				break;
			case Add:
				switch( oldValue ) {
					case Unknown:
					case Add:
					case View:
						if( editObj == null ) {
							if( focus != null ) {
								if( ! focus.getIsNew() ) {
									throw new CFLibUsageException( getClass(),
										S_ProcName,
										Inz.x("cflibjavafx.common.MustBeNew"),
										Inz.s("cflibjavafx.common.MustBeNew") );
								}
								editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
								if( editObj == null ) {
									throw new CFLibUsageException( getClass(),
										S_ProcName,
										Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
										Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
								}
							}
							else {
								throw new CFLibNullArgumentException( getClass(),
									S_ProcName,
									0,
									"focus" );
							}
						}
						break;
					case Edit:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							Inz.x("cflibjavafx.common.CannotTransitionEditToAdd"),
							Inz.s("cflibjavafx.common.CannotTransitionEditToAdd") );
					case Update:
						if( ( editObj == null ) || ( ! editObj.getIsNew() ) ) {
							throw new CFLibUsageException( getClass(),
								S_ProcName,
								Inz.x("cflibjavafx.common.CannotTransitionUpdateToAdd"),
								Inz.s("cflibjavafx.common.CannotTransitionUpdateToAdd") );
						}
						break;
					case Delete:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							Inz.x("cflibjavafx.common.CannotTransitionDeleteToAdd"),
							Inz.s("cflibjavafx.common.CannotTransitionDeleteToAdd") );
					default:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							Inz.x("cflibjavafx.common.CannotTransitionDefaultToAdd"),
							Inz.s("cflibjavafx.common.CannotTransitionDefaultToAdd") );
				}
				break;
			case View:
				switch( oldValue ) {
					case Unknown:
						break;
					case View:
						break;
					case Edit:
						break;
					case Update:
						break;
					case Delete:
						break;
					default:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							String.format(Inz.x("cflibjavafx.common.CannotTransitionOldValueToView"), oldValue),
							String.format(Inz.s("cflibjavafx.common.CannotTransitionOldValueToView"), oldValue) );
				}
				if( editObj != null ) {
					editObj.endEdit();
					editObj = null;
				}
				break;
			case Edit:
				switch( oldValue ) {
					case Unknown:
						if( editObj == null ) {
							editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
							if( editObj == null ) {
								throw new CFLibUsageException( getClass(),
									S_ProcName,
									Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
									Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
							}
						}
						break;
					case View:
						if( editObj == null ) {
							editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
							if( editObj == null ) {
								throw new CFLibUsageException( getClass(),
									S_ProcName,
									Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
									Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
							}
						}
						break;
					case Edit:
						if( editObj == null ) {
							editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
							if( editObj == null ) {
								throw new CFLibUsageException( getClass(),
									S_ProcName,
									Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
									Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
							}
						}
						break;
					case Update:
						if( editObj == null ) {
							throw new CFLibUsageException( getClass(),
								S_ProcName,
								String.format(Inz.x("cflibjavafx.common.CannotTransitionOldValueToEdit"), oldValue),
								String.format(Inz.s("cflibjavafx.common.CannotTransitionOldValueToEdit"), oldValue) );
						}
						break;
					default:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							String.format(Inz.x("cflibjavafx.common.CannotTransitionOldValueToEdit"), oldValue),
							String.format(Inz.s("cflibjavafx.common.CannotTransitionOldValueToEdit"), oldValue) );
				}
				break;
			case Update:
				if( ( oldValue != CFPane.PaneMode.Edit ) && ( oldValue != CFPane.PaneMode.Add ) ) {
					throw new CFLibUsageException( getClass(),
						S_ProcName,
						String.format(Inz.x("cflibjavafx.common.CannotTransitionOldValueToUpdate"), oldValue),
						String.format(Inz.s("cflibjavafx.common.CannotTransitionOldValueToUpdate"), oldValue) );
				}
				super.setPaneMode( value );
				if( editObj != null ) {
					postFields();
					if( editObj.getIsNew() ) {
						focus = (ICFSecTableInfoObj)editObj.create();
						setJavaFXFocus( focus );
					}
					else {
						editObj.update();
					}
					editObj = null;
				}
				setPaneMode( CFPane.PaneMode.View );
				break;
			case Delete:
				switch( oldValue ) {
					case View:
						if( focus != null ) {
							if( editObj == null ) {
								editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
								if( editObj == null ) {
								throw new CFLibUsageException( getClass(),
									S_ProcName,
									Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
									Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
								}
							}
						}
						break;
					case Edit:
						if( focus != null ) {
							if( editObj == null ) {
								editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
								if( editObj == null ) {
								throw new CFLibUsageException( getClass(),
									S_ProcName,
									Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
									Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
								}
							}
						}
						break;
					case Update:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							String.format(Inz.x("cflibjavafx.common.CannotTransitionOldValueToDelete"), oldValue),
							String.format(Inz.s("cflibjavafx.common.CannotTransitionOldValueToDelete"), oldValue) );
					case Delete:
						if( editObj == null ) {
							editObj = (ICFSecTableInfoEditObj)focus.beginEdit();
							if( editObj == null ) {
								throw new CFLibUsageException( getClass(),
									S_ProcName,
									Inz.x("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition"),
									Inz.s("cflibjavafx.common.ExpectedBeginEditToReturnNewEdition") );
							}
						}
						break;
					default:
						throw new CFLibUsageException( getClass(),
							S_ProcName,
							String.format(Inz.x("cflibjavafx.common.CannotTransitionOldValueToDelete"), oldValue),
							String.format(Inz.s("cflibjavafx.common.CannotTransitionOldValueToDelete"), oldValue) );
				}
				editObj.deleteInstance();
				editObj = null;
				setJavaFXFocus( null );
				setPaneMode( CFPane.PaneMode.Unknown );
				break;
			default:
				switch( oldValue ) {
					case Unknown:
						break;
					default:
						if( editObj != null ) {
							editObj.endEdit();
							editObj = null;
						}
						break;
				}
				break;
		}
		super.setPaneMode( value );
		populateFields();
		adjustComponentEnableStates();
	}

	public void adjustComponentEnableStates() {
		CFPane.PaneMode mode = getPaneMode();
		boolean isEditing;
		switch( mode ) {
			case Unknown:
			case View:
			case Delete:
				isEditing = false;
				break;
			case Add:
			case Edit:
			case Update:
				isEditing = true;
				break;
			default:
				isEditing = false;
				break;
		}
		if( isEditing ) {
			ICFSecTableInfoObj focus = getJavaFXFocusAsTableInfo();
			if( focus == null ) {
				isEditing = false;
			}
			else if( null == focus.getEdit() ) {
				isEditing = false;
			}
		}
		if( javafxReferenceParentSuperRef != null ) {
			javafxReferenceParentSuperRef.setCustomDisable( ! isEditing );
		}
		if( javafxEditorTableInfoId != null ) {
			javafxEditorTableInfoId.setDisable( true );
		}
		if( javafxEditorSchemaName != null ) {
			javafxEditorSchemaName.setDisable( ! isEditing );
		}
		if( javafxEditorTableName != null ) {
			javafxEditorTableName.setDisable( ! isEditing );
		}
		if( javafxEditorBackingClassCode != null ) {
			javafxEditorBackingClassCode.setDisable( ! isEditing );
		}
		if( javafxEditorRuntimeClassCode != null ) {
			javafxEditorRuntimeClassCode.setDisable( ! isEditing );
		}
		if( javafxEditorHasHistory != null ) {
			javafxEditorHasHistory.setDisable( ! isEditing );
		}
		if( javafxEditorIsMutable != null ) {
			javafxEditorIsMutable.setDisable( ! isEditing );
		}
		if( javafxEditorSecScopeName != null ) {
			javafxEditorSecScopeName.setDisable( ! isEditing );
		}
		if( javafxEditorCodeVis != null ) {
			javafxEditorCodeVis.setDisable( ! isEditing );
		}
	}
}
