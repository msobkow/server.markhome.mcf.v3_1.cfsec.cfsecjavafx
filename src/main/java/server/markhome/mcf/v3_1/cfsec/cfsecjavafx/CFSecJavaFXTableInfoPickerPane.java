// Description: Java 25 JavaFX Picker of Obj Pane implementation for TableInfo.

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
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
 *	CFSecJavaFXTableInfoPickerPane JavaFX Pick Obj Pane implementation
 *	for TableInfo.
 */
public class CFSecJavaFXTableInfoPickerPane
extends CFBorderPane
implements ICFSecJavaFXTableInfoPaneList
{
	public static String S_FormName = "Choose Table Information";
	protected ICFSecJavaFXSchema javafxSchema = null;
	protected Collection<ICFSecTableInfoObj> javafxDataCollection = null;
	protected ObservableList<ICFSecTableInfoObj> observableListOfTableInfo = null;
	protected TableColumn<ICFSecTableInfoObj, $implIJavaOptAtomType$> tableColumnTableInfoId = null;
	protected TableColumn<ICFSecTableInfoObj, String> tableColumnSchemaName = null;
	protected TableColumn<ICFSecTableInfoObj, String> tableColumnTableName = null;
	protected TableColumn<ICFSecTableInfoObj, Integer> tableColumnBackingClassCode = null;
	protected TableColumn<ICFSecTableInfoObj, Integer> tableColumnRuntimeClassCode = null;
	protected TableColumn<ICFSecTableInfoObj, Boolean> tableColumnHasHistory = null;
	protected TableColumn<ICFSecTableInfoObj, Boolean> tableColumnIsMutable = null;
	protected TableColumn<ICFSecTableInfoObj, String> tableColumnSecScopeName = null;
	protected TableColumn<ICFSecTableInfoObj, String> tableColumnCodeVis = null;
	protected TableColumn<ICFSecTableInfoObj, ICFSecTableInfoObj> tableColumnParentSuperRef = null;
	protected TableView<ICFSecTableInfoObj> dataTable = null;
	protected CFHBox hboxMenu = null;
	public final String S_ColumnNames[] = { "Name" };
	protected ICFFormManager cfFormManager = null;
	protected ICFSecJavaFXTableInfoChosen invokeWhenChosen = null;
	protected ICFLibAnyObj javafxContainer = null;
	protected CFButton buttonCancel = null;
	protected CFButton buttonChooseNone = null;
	protected CFButton buttonChooseSelected = null;
	protected ScrollPane scrollMenu = null;
	public CFSecJavaFXTableInfoPickerPane( ICFFormManager formManager,
		ICFSecJavaFXSchema argSchema,
		ICFSecTableInfoObj argFocus,
		ICFLibAnyObj argContainer,
		Collection<ICFSecTableInfoObj> argDataCollection,
		ICFSecJavaFXTableInfoChosen whenChosen )
	{
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
		if( whenChosen == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				6,
				"whenChosen" );
		}
		invokeWhenChosen = whenChosen;
		// argFocus is optional; focus may be set later during execution as
		// conditions of the runtime change.
		javafxSchema = argSchema;
		javaFXFocus = argFocus;
		javafxContainer = argContainer;
		setJavaFXDataCollection( argDataCollection );
		dataTable = new TableView<ICFSecTableInfoObj>();
		tableColumnTableInfoId = new TableColumn<ICFSecTableInfoObj,$implIJavaOptAtomType$>( "TableInfoId" );
		tableColumnTableInfoId.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,$implIJavaOptAtomType$>,ObservableValue<$implIJavaOptAtomType$> >() {
			public ObservableValue<$implIJavaOptAtomType$> call( CellDataFeatures<ICFSecTableInfoObj, $implIJavaOptAtomType$> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredTableInfoId();
					$implIJavaOptAtomType$ wrapped = $implIJavaOptAtomType$.valueOf( value );
					ReadOnlyObjectWrapper<$implIJavaOptAtomType$> observable = new ReadOnlyObjectWrapper<$implIJavaOptAtomType$>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnTableInfoId.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,$implIJavaOptAtomType$>,TableCell<ICFSecTableInfoObj,$implIJavaOptAtomType$>>() {
			@Override public TableCell<ICFSecTableInfoObj,$implIJavaOptAtomType$> call(
				TableColumn<ICFSecTableInfoObj,$implIJavaOptAtomType$> arg)
			{
				return new CFInt32TableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnTableInfoId );
		tableColumnSchemaName = new TableColumn<ICFSecTableInfoObj,String>( "SchemaName" );
		tableColumnSchemaName.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,String>,ObservableValue<String> >() {
			public ObservableValue<String> call( CellDataFeatures<ICFSecTableInfoObj, String> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredSchemaName();
					ReadOnlyObjectWrapper<$implIJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implIJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnSchemaName.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,String>,TableCell<ICFSecTableInfoObj,String>>() {
			@Override public TableCell<ICFSecTableInfoObj,String> call(
				TableColumn<ICFSecTableInfoObj,String> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnSchemaName );
		tableColumnTableName = new TableColumn<ICFSecTableInfoObj,String>( "TableName" );
		tableColumnTableName.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,String>,ObservableValue<String> >() {
			public ObservableValue<String> call( CellDataFeatures<ICFSecTableInfoObj, String> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredTableName();
					ReadOnlyObjectWrapper<$implIJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implIJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnTableName.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,String>,TableCell<ICFSecTableInfoObj,String>>() {
			@Override public TableCell<ICFSecTableInfoObj,String> call(
				TableColumn<ICFSecTableInfoObj,String> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnTableName );
		tableColumnBackingClassCode = new TableColumn<ICFSecTableInfoObj,Integer>( "BackingClassCode" );
		tableColumnBackingClassCode.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,Integer>,ObservableValue<Integer> >() {
			public ObservableValue<Integer> call( CellDataFeatures<ICFSecTableInfoObj, Integer> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredBackingClassCode();
					Integer wrapped = Integer.valueOf( value );
					ReadOnlyObjectWrapper<Integer> observable = new ReadOnlyObjectWrapper<Integer>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnBackingClassCode.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,Integer>,TableCell<ICFSecTableInfoObj,Integer>>() {
			@Override public TableCell<ICFSecTableInfoObj,Integer> call(
				TableColumn<ICFSecTableInfoObj,Integer> arg)
			{
				return new CFInt32TableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnBackingClassCode );
		tableColumnRuntimeClassCode = new TableColumn<ICFSecTableInfoObj,Integer>( "RuntimeClassCode" );
		tableColumnRuntimeClassCode.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,Integer>,ObservableValue<Integer> >() {
			public ObservableValue<Integer> call( CellDataFeatures<ICFSecTableInfoObj, Integer> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredRuntimeClassCode();
					Integer wrapped = Integer.valueOf( value );
					ReadOnlyObjectWrapper<Integer> observable = new ReadOnlyObjectWrapper<Integer>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnRuntimeClassCode.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,Integer>,TableCell<ICFSecTableInfoObj,Integer>>() {
			@Override public TableCell<ICFSecTableInfoObj,Integer> call(
				TableColumn<ICFSecTableInfoObj,Integer> arg)
			{
				return new CFInt32TableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnRuntimeClassCode );
		tableColumnHasHistory = new TableColumn<ICFSecTableInfoObj,Boolean>( "HasHistory" );
		tableColumnHasHistory.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,Boolean>,ObservableValue<Boolean> >() {
			public ObservableValue<Boolean> call( CellDataFeatures<ICFSecTableInfoObj, Boolean> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredHasHistory();
					Boolean wrapped = Boolean.valueOf( value );
					ReadOnlyObjectWrapper<Boolean> observable = new ReadOnlyObjectWrapper<Boolean>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnHasHistory.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,Boolean>,TableCell<ICFSecTableInfoObj,Boolean>>() {
			@Override public TableCell<ICFSecTableInfoObj,Boolean> call(
				TableColumn<ICFSecTableInfoObj,Boolean> arg)
			{
				return new CFBoolTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnHasHistory );
		tableColumnIsMutable = new TableColumn<ICFSecTableInfoObj,Boolean>( "IsMutable" );
		tableColumnIsMutable.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,Boolean>,ObservableValue<Boolean> >() {
			public ObservableValue<Boolean> call( CellDataFeatures<ICFSecTableInfoObj, Boolean> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredIsMutable();
					Boolean wrapped = Boolean.valueOf( value );
					ReadOnlyObjectWrapper<Boolean> observable = new ReadOnlyObjectWrapper<Boolean>();
					observable.setValue( wrapped );
					return( observable );
				}
			}
		});
		tableColumnIsMutable.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,Boolean>,TableCell<ICFSecTableInfoObj,Boolean>>() {
			@Override public TableCell<ICFSecTableInfoObj,Boolean> call(
				TableColumn<ICFSecTableInfoObj,Boolean> arg)
			{
				return new CFBoolTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnIsMutable );
		tableColumnSecScopeName = new TableColumn<ICFSecTableInfoObj,String>( "SecScopeName" );
		tableColumnSecScopeName.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,String>,ObservableValue<String> >() {
			public ObservableValue<String> call( CellDataFeatures<ICFSecTableInfoObj, String> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredSecScopeName();
					ReadOnlyObjectWrapper<$implIJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implIJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnSecScopeName.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,String>,TableCell<ICFSecTableInfoObj,String>>() {
			@Override public TableCell<ICFSecTableInfoObj,String> call(
				TableColumn<ICFSecTableInfoObj,String> arg)
			{
				return new CFStringTableCell<ICFSecTableInfoObj>();
			}
		});
		dataTable.getColumns().add( tableColumnSecScopeName );
		tableColumnCodeVis = new TableColumn<ICFSecTableInfoObj,String>( "CodeVis" );
		tableColumnCodeVis.setCellValueFactory( new Callback<CellDataFeatures<ICFSecTableInfoObj,String>,ObservableValue<String> >() {
			public ObservableValue<String> call( CellDataFeatures<ICFSecTableInfoObj, String> p ) {
				ICFSecTableInfoObj obj = p.getValue();
				if( obj == null ) {
					return( null );
				}
				else {
					$implIJavaAtomType$ value = obj.getRequiredCodeVis();
					ReadOnlyObjectWrapper<$implIJavaAtomType$> observable = new ReadOnlyObjectWrapper<$implIJavaAtomType$>();
					observable.setValue( value );
					return( observable );
				}
			}
		});
		tableColumnCodeVis.setCellFactory( new Callback<TableColumn<ICFSecTableInfoObj,String>,TableCell<ICFSecTableInfoObj,String>>() {
			@Override public TableCell<ICFSecTableInfoObj,String> call(
				TableColumn<ICFSecTableInfoObj,String> arg)
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
					if( buttonChooseSelected != null ) {
						if( newValue != null ) {
							buttonChooseSelected.setDisable( false );
						}
						else {
							buttonChooseSelected.setDisable( true );
						}
					}
				}
			});
		hboxMenu = new CFHBox( 10 );
		buttonCancel = new CFButton();
		buttonCancel.setMinWidth( 200 );
		buttonCancel.setText( "Cancel" );
		buttonCancel.setOnAction( new EventHandler<ActionEvent>() {
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
		hboxMenu.getChildren().add( buttonCancel );
		buttonChooseNone = new CFButton();
		buttonChooseNone.setMinWidth( 200 );
		buttonChooseNone.setText( "ChooseNone" );
		buttonChooseNone.setOnAction( new EventHandler<ActionEvent>() {
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
					invokeWhenChosen.choseTableInfo( null );
					cfFormManager.closeCurrentForm();
				}
				catch( Throwable t ) {
					CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
				}
			}
		});
		hboxMenu.getChildren().add( buttonChooseNone );
		buttonChooseSelected = new CFButton();
		buttonChooseSelected.setMinWidth( 200 );
		buttonChooseSelected.setText( "ChooseSelected" );
		buttonChooseSelected.setOnAction( new EventHandler<ActionEvent>() {
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
					invokeWhenChosen.choseTableInfo( selectedInstance );
					cfFormManager.closeCurrentForm();
				}
				catch( Throwable t ) {
					CFConsole.formException( S_FormName, ((CFButton)e.getSource()).getText(), t );
				}
			}
		});
		hboxMenu.getChildren().add( buttonChooseSelected );
		if( argFocus != null ) {
			dataTable.getSelectionModel().select( argFocus );
		}

		scrollMenu = new ScrollPane();
		scrollMenu.setVbarPolicy( ScrollBarPolicy.NEVER );
		scrollMenu.setHbarPolicy( ScrollBarPolicy.AS_NEEDED );
		scrollMenu.setFitToHeight( true );
		scrollMenu.setContent( hboxMenu );

		setTop( scrollMenu );
		setCenter( dataTable );
		adjustListButtons();
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
		if( dataTable == null ) {
			return;
		}
	}

	public ICFSecTableInfoObj getJavaFXFocusAsTableInfo() {
		return( (ICFSecTableInfoObj)getJavaFXFocus() );
	}

	public void setJavaFXFocusAsTableInfo( ICFSecTableInfoObj value ) {
		setJavaFXFocus( value );
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

	public Collection<ICFSecTableInfoObj> getJavaFXDataCollection() {
		return( javafxDataCollection );
	}

	public void setJavaFXDataCollection( Collection<ICFSecTableInfoObj> value ) {
		final String S_ProcName = "setJavaFXDataCollection";
		javafxDataCollection = value;
		observableListOfTableInfo = FXCollections.observableArrayList();
		if( javafxDataCollection != null ) {
				Iterator<ICFSecTableInfoObj> iter = javafxDataCollection.iterator();
				while( iter.hasNext() ) {
					observableListOfTableInfo.add( iter.next() );
				}
				observableListOfTableInfo.sort( compareTableInfoByQualName );
		}
		if( dataTable != null ) {
			dataTable.setItems( observableListOfTableInfo );
			// Hack from stackoverflow to fix JavaFX TableView refresh issue
			((TableColumn)dataTable.getColumns().get(0)).setVisible( false );
			((TableColumn)dataTable.getColumns().get(0)).setVisible( true );
		}
	}

	public ICFLibAnyObj getJavaFXContainer() {
		return( javafxContainer );
	}

	public void setJavaFXContainer( ICFLibAnyObj value ) {
		javafxContainer = value;
	}

	public void adjustListButtons() {
		boolean enableState;
		ICFSecTableInfoObj selectedObj = getJavaFXFocusAsTableInfo();
		if( selectedObj == null ) {
			enableState = false;
		}
		else {
			enableState = true;
		}

		if( buttonChooseSelected != null ) {
			buttonChooseSelected.setDisable( ! enableState );
		}
		if( buttonChooseNone != null ) {
			buttonChooseNone.setDisable( false );
		}
		if( buttonCancel != null ) {
			buttonCancel.setDisable( false );
		}

	}
}

