// Description: Java 25 JavaFX Finder Form implementation for TableInfo.

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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

/**
 *	CFSecJavaFXTableInfoFinderForm JavaFX Finder Form implementation
 *	for TableInfo.
 */
public class CFSecJavaFXTableInfoFinderForm
extends CFBorderPane
implements ICFSecJavaFXTableInfoPaneCommon,
	ICFForm
{
	public static String S_FormName = "Find Table Information";
	protected ICFFormManager cfFormManager = null;
	protected ICFSecJavaFXSchema javafxSchema = null;
	protected boolean javafxIsInitializing = true;
	protected ScrollPane scrollMenu = null;
	protected CFHBox hboxMenu = null;
	protected CFVBox vboxMenuAdd = null;
	protected ScrollPane scrollMenuAdd = null;
	protected CFButton buttonAdd = null;
	protected CFButton buttonCancelAdd = null;
	protected CFButton buttonAddTableInfo = null;
	protected CFButton buttonViewSelected = null;
	protected CFButton buttonEditSelected = null;
	protected CFButton buttonClose = null;
	protected CFButton buttonDeleteSelected = null;
	protected List<ICFSecTableInfoObj> listOfTableInfo = null;
	protected ObservableList<ICFSecTableInfoObj> observableListOfTableInfo = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnTableInfoId = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnSchemaName = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnTableName = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnBackingClassCode = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnRuntimeClassCode = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnHasHistory = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnIsMutable = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnSecScopeName = null;
	protected TableColumn<ICFSecTableInfoObj, $implJavaOptAtomType$> tableColumnCodeVis = null;
	protected TableColumn<ICFSecTableInfoObj, ICFSecTableInfoObj> tableColumnParentSuperRef = null;
	protected TableView<ICFSecTableInfoObj> dataTable = null;

	class ViewEditClosedCallback implements ICFFormClosedCallback {
		public ViewEditClosedCallback() {
		}

		@Override
		public void formClosed( ICFLibAnyObj affectedObject ) {
			if( affectedObject != null ) {
				refreshMe();
			}
		}
	}

	protected ViewEditClosedCallback viewEditClosedCallback = null;

	public ICFFormClosedCallback getViewEditClosedCallback() {
		if( viewEditClosedCallback == null ) {
			viewEditClosedCallback = new ViewEditClosedCallback();
		}
		return( viewEditClosedCallback );
	}

	class DeleteCallback implements ICFDeleteCallback {
		public DeleteCallback() {
		}

		@Override
		public void deleted( ICFLibAnyObj deletedObject ) {
			if( deletedObject != null ) {
				refreshMe();
			}
		}

		@Override
		public void formClosed( ICFLibAnyObj affectedObject ) {
			if( affectedObject != null ) {
				refreshMe();
			}
		}
	}

	protected DeleteCallback deleteCallback = null;

	public ICFDeleteCallback getDeleteCallback() {
		if( deleteCallback == null ) {
			deleteCallback = new DeleteCallback();
		}
		return( deleteCallback );
	}

	public CFSecJavaFXTableInfoFinderForm( ICFFormManager formManager, ICFSecJavaFXSchema argSchema ) {
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
		javafxSchema = argSchema;
		dataTable = new TableView<ICFSecTableInfoObj>();
		tableColumnTableInfoId = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "TableInfoId" );
		tableColumnTableInfoId.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredTableInfoId();
					$implJavaOptAtomType$ wrapped = $implJavaOptAtomType$.valueOf( value );
					ReadOnlyObjectWrapper<$implJavaOptAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaOptAtomType$>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnTableInfoId.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFInt32TableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnTableInfoId );
		tableColumnSchemaName = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "SchemaName" );
		tableColumnSchemaName.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredSchemaName();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnSchemaName.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnSchemaName );
		tableColumnTableName = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "TableName" );
		tableColumnTableName.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredTableName();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnTableName.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnTableName );
		tableColumnBackingClassCode = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "BackingClassCode" );
		tableColumnBackingClassCode.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredBackingClassCode();
					$implJavaOptAtomType$ wrapped = $implJavaOptAtomType$.valueOf( value );
					ReadOnlyObjectWrapper<$implJavaOptAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaOptAtomType$>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnBackingClassCode.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFInt32TableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnBackingClassCode );
		tableColumnRuntimeClassCode = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "RuntimeClassCode" );
		tableColumnRuntimeClassCode.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredRuntimeClassCode();
					$implJavaOptAtomType$ wrapped = $implJavaOptAtomType$.valueOf( value );
					ReadOnlyObjectWrapper<$implJavaOptAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaOptAtomType$>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnRuntimeClassCode.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFInt32TableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnRuntimeClassCode );
		tableColumnHasHistory = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "HasHistory" );
		tableColumnHasHistory.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredHasHistory();
					$implJavaOptAtomType$ wrapped = $implJavaOptAtomType$.valueOf( value );
					ReadOnlyObjectWrapper<$implJavaOptAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaOptAtomType$>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnHasHistory.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFBoolTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnHasHistory );
		tableColumnIsMutable = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "IsMutable" );
		tableColumnIsMutable.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredIsMutable();
					$implJavaOptAtomType$ wrapped = $implJavaOptAtomType$.valueOf( value );
					ReadOnlyObjectWrapper<$implJavaOptAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaOptAtomType$>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnIsMutable.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFBoolTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnIsMutable );
		tableColumnSecScopeName = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "SecScopeName" );
		tableColumnSecScopeName.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredSecScopeName();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnSecScopeName.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnSecScopeName );
		tableColumnCodeVis = new TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>( "CodeVis" );
		tableColumnCodeVis.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implJavaOptAtomType$>,ObservableValue<$implJavaOptAtomType$> >() {
			public ObservableValue<$implJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implJavaAtomType$ value = obj.getRequiredCodeVis();
					ReadOnlyObjectWrapper<$implJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnCodeVis.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implJavaOptAtomType$> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnCodeVis );
		tableColumnParentSuperRef = new TableColumn<ICFSecTableInfoObj, ICFSecTableInfoObj>( "Superclass Table Reference" );
		tableColumnParentSuperRef.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,ICFSecTableInfoObj>,ObservableValue<ICFSecTableInfoObj> >() {
			public ObservableValue<ICFSecTableInfoObj> call( CellDataFeatures<ICFSecTableInfoObj, ICFSecTableInfoObj> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					ICFSecTableInfoObj ref = obj.getOptionalParentSuperRef();
					ReadOnlyObjectWrapper<ICFSecTableInfoObj> observable = new ReadOnlyObjectWrapper<ICFSecTableInfoObj>();
					observable.setValue( ref );
					return( observable );
				}
			}
		});
		tableColumnParentSuperRef.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,ICFSecTableInfoObj>,TableCell<ICFSecTableInfoObj,ICFSecTableInfoObj>>() {
			@Override public TableCell<ICFSecTableInfoObj,ICFSecTableInfoObj> call(
				TableColumn<ICFSecTableInfoObj,ICFSecTableInfoObj> arg)
			{
				return new CFReferenceTableCell<ICFSecTableInfoObj,ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnParentSuperRef );
		dataTable.getSelectionModel().selectedItemProperty().addListener(
			new ChangeListener<ICFSecTableInfoObj>() {
				@Override public void changed( ObservableValue<? extends ICFSecTableInfoObj> observable,
					ICFSecTableInfoObj oldValue,
					ICFSecTableInfoObj newValue )
				{
					setJavaFXFocus( newValue );
				}
			});

		scrollMenu = new ScrollPane();
		scrollMenu.setVbarPolicy( ScrollBarPolicy.NEVER );
		scrollMenu.setHbarPolicy( ScrollBarPolicy.AS_NEEDED );
		scrollMenu.setFitToHeight( true );
		scrollMenu.setContent( getHBoxMenu() );

		setTop( scrollMenu );
		setCenter( dataTable );

		refreshMe();
		javafxIsInitializing = false;
		adjustFinderButtons();
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

	public void forceCancelAndClose() {
		if( cfFormManager != null ) {
			if( cfFormManager.getCurrentForm() == this ) {
				cfFormManager.closeCurrentForm();
			}
		}
	}

	public ICFSecJavaFXSchema getJavaFXSchema() {
		return( javafxSchema );
	}

	protected class CompareCFButtonByText
	implements Comparator<CFButton>
	{
		public CompareCFButtonByText() {
		}

		@Override public int compare( CFButton lhs, CFButton rhs ) {
			if( lhs == null ) {
				if( rhs == null ) {
					return( 0 );
				}
				else {
					return( -1 );
				}
			}
			else if( rhs == null ) {
				return( 1 );
			}
			else {
				int retval = lhs.getText().compareTo( rhs.getText() );
				return( retval );
			}
		}
	}

	public CFHBox getHBoxMenu() {
		if( hboxMenu == null ) {
			hboxMenu = new CFHBox( 10 );

			buttonAddTableInfo = new CFButton();
			buttonAddTableInfo.setMinWidth( 200 );
			buttonAddTableInfo.setText( "Add Table Information..." );
			buttonAddTableInfo.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFSecTableInfoObj obj = (ICFSecTableInfoObj)schemaObj.getTableInfoTableObj().newInstance();
						ICFSecTableInfoEditObj edit = (ICFSecTableInfoEditObj)( obj.beginEdit() );
						if( edit == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"edit" );
						}
						CFBorderPane frame = javafxSchema.getTableInfoFactory().newAddForm( cfFormManager, obj, getViewEditClosedCallback(), true );
						ICFSecJavaFXTableInfoPaneCommon jpanelCommon = (ICFSecJavaFXTableInfoPaneCommon)frame;
						jpanelCommon.setPaneMode( CFPane.PaneMode.Add );
						cfFormManager.pushForm( frame );
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonAddTableInfo );

			buttonViewSelected = new CFButton();
			buttonViewSelected.setMinWidth( 200 );
			buttonViewSelected.setText( "View Selected" );
			buttonViewSelected.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFSecTableInfoObj selectedInstance = getJavaFXFocusAsTableInfo();
						if( selectedInstance != null ) {
							int classCode = selectedInstance.getClassCode();
							ICFSecSchema.ClassMapEntry entry = ICFSecSchema.getClassMapByRuntimeClassCode(classCode);
							int backingClassCode = entry.getBackingClassCode();
							if( entry.getSchemaName().equals("CFSec") && backingClassCode == ICFSecTableInfo.CLASS_CODE ) {
								CFBorderPane frame = javafxSchema.getTableInfoFactory().newViewEditForm( cfFormManager, selectedInstance, getViewEditClosedCallback(), false );
								((ICFSecJavaFXTableInfoPaneCommon)frame).setPaneMode( CFPane.PaneMode.View );
								cfFormManager.pushForm( frame );
							}
							else {
								throw new CFLibUnsupportedClassException( getClass(),
									S_ProcName,
									"selectedInstance",
									selectedInstance,
									"ICFSecTableInfoObj" );
							}
						}
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonViewSelected );

			buttonEditSelected = new CFButton();
			buttonEditSelected.setMinWidth( 200 );
			buttonEditSelected.setText( "Edit Selected" );
			buttonEditSelected.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFSecTableInfoObj selectedInstance = getJavaFXFocusAsTableInfo();
						if( selectedInstance != null ) {
							int classCode = selectedInstance.getClassCode();
							ICFSecSchema.ClassMapEntry entry = ICFSecSchema.getClassMapByRuntimeClassCode(classCode);
							int backingClassCode = entry.getBackingClassCode();
							if( entry.getSchemaName().equals("CFSec") && backingClassCode == ICFSecTableInfo.CLASS_CODE ) {
								CFBorderPane frame = javafxSchema.getTableInfoFactory().newViewEditForm( cfFormManager, selectedInstance, getViewEditClosedCallback(), false );
								((ICFSecJavaFXTableInfoPaneCommon)frame).setPaneMode( CFPane.PaneMode.Edit );
								cfFormManager.pushForm( frame );
							}
							else {
								throw new CFLibUnsupportedClassException( getClass(),
									S_ProcName,
									"selectedInstance",
									selectedInstance,
									"ICFSecTableInfoObj" );
							}
						}
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonEditSelected );

			buttonDeleteSelected = new CFButton();
			buttonDeleteSelected.setMinWidth( 200 );
			buttonDeleteSelected.setText( "Delete Selected" );
			buttonDeleteSelected.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
						if( schemaObj == null ) {
							throw new CFLibNullArgumentException( getClass(),
								S_ProcName,
								0,
								"schemaObj" );
						}
						ICFSecTableInfoObj selectedInstance = getJavaFXFocusAsTableInfo();
						if( selectedInstance != null ) {
							int classCode = selectedInstance.getClassCode();
							ICFSecSchema.ClassMapEntry entry = ICFSecSchema.getClassMapByRuntimeClassCode(classCode);
							int backingClassCode = entry.getBackingClassCode();
							if( entry.getSchemaName().equals("CFSec") && backingClassCode == ICFSecTableInfo.CLASS_CODE ) {
								CFBorderPane frame = javafxSchema.getTableInfoFactory().newAskDeleteForm( cfFormManager, selectedInstance, getDeleteCallback() );
								((ICFSecJavaFXTableInfoPaneCommon)frame).setPaneMode( CFPane.PaneMode.View );
								cfFormManager.pushForm( frame );
							}
							else {
								throw new CFLibUnsupportedClassException( getClass(),
									S_ProcName,
									"selectedInstance",
									selectedInstance,
									"ICFSecTableInfoObj" );
							}
						}
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonDeleteSelected );

			buttonClose = new CFButton();
			buttonClose.setMinWidth( 200 );
			buttonClose.setText( "Close" );
			buttonClose.setOnAction( new EventHandler<ActionEvent>() {
				@Override public void handle( ActionEvent e ) {
					final String S_ProcName = "handle";
					try {
						cfFormManager.closeCurrentForm();
					}
					catch( Throwable t ) {
						CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
					}
				}
			});
			hboxMenu.getChildren().add( buttonClose );
		}
		return( hboxMenu );
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
		adjustFinderButtons();
	}

	public ICFSecTableInfoObj getJavaFXFocusAsTableInfo() {
		return( (ICFSecTableInfoObj)getJavaFXFocus() );
	}

	public void setJavaFXFocusAsTableInfo( ICFSecTableInfoObj value ) {
		setJavaFXFocus( value );
	}

	public void adjustFinderButtons() {
		ICFSecTableInfoObj selectedObj = getJavaFXFocusAsTableInfo();
		boolean disableState;
		if( selectedObj == null ) {
			disableState = true;
		}
		else {
			disableState = false;
		}
		boolean inAddMode;
		if( getLeft() == null ) {
			inAddMode = false;
		}
		else {
			inAddMode = true;
			disableState = true;
		}

		if( buttonViewSelected != null ) {
			buttonViewSelected.setDisable( disableState );
		}
		if( buttonEditSelected != null ) {
			buttonEditSelected.setDisable( disableState );
		}
		if( buttonDeleteSelected != null ) {
			buttonDeleteSelected.setDisable( disableState );
		}
		if( buttonAddTableInfo != null ) {
			buttonAddTableInfo.setDisable( false );
		}

	}

	public class TableInfoByQualNameComparator
	implements Comparator<ICFSecTableInfoObj>
	{
		public TableInfoByQualNameComparator() {
		}

		public int compare( ICFSecTableInfoObj lhs, ICFSecTableInfoObj rhs ) {
			if( lhs == null ) {
				if( rhs == null ) {
					return( 0 );
				}
				else {
					return( -1 );
				}
			}
			else if( rhs == null ) {
				return( 1 );
			}
			else {
				String lhsValue = lhs.getObjQualifiedName();
				String rhsValue = rhs.getObjQualifiedName();
				if( lhsValue == null ) {
					if( rhsValue == null ) {
						return( 0 );
					}
					else {
						return( -1 );
					}
				}
				else if( rhsValue == null ) {
					return( 1 );
				}
				else {
					return( lhsValue.compareTo( rhsValue ) );
				}
			}
		}
	}

	protected TableInfoByQualNameComparator compareTableInfoByQualName = new TableInfoByQualNameComparator();

	public void loadData( boolean forceReload ) {
		ICFSecSchemaObj schemaObj = (ICFSecSchemaObj)javafxSchema.getSchema();
		if( ( listOfTableInfo == null ) || forceReload ) {
			observableListOfTableInfo = null;
			listOfTableInfo = schemaObj.getTableInfoTableObj().readAllTableInfo( javafxIsInitializing );
			if( listOfTableInfo != null ) {
				observableListOfTableInfo = FXCollections.observableArrayList( listOfTableInfo );
				observableListOfTableInfo.sort( compareTableInfoByQualName );
			}
			else {
				observableListOfTableInfo = FXCollections.observableArrayList();
			}
			dataTable.setItems( observableListOfTableInfo );
			// Hack from stackoverflow to fix JavaFX TableView refresh issue
			((TableColumn)dataTable.getColumns().get(0)).setVisible( false );
			((TableColumn)dataTable.getColumns().get(0)).setVisible( true );
		}
	}

	public void refreshMe() {
		loadData( true );
	}
}
