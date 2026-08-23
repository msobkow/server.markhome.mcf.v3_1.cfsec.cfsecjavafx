// Description: Java 25 JavaFX Display Element Factory for TableInfo.

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
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.inz.Inz;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import org.apache.commons.codec.binary.Base64;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;

/**
 *	CFSecJavaFXTableInfoFactory JavaFX Display Element Factory
 *	for TableInfo.
 */
public class CFSecJavaFXTableInfoFactory
implements ICFSecJavaFXTableInfoFactory
{
	protected ICFSecJavaFXSchema javafxSchema = null;

	public CFSecJavaFXTableInfoFactory( ICFSecJavaFXSchema argSchema ) {
		final String S_ProcName = "construct-schema";
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( this.getClass(),
				S_ProcName,
				1,
				"argSchema" );
		}
		javafxSchema = argSchema;
	}

	public CFGridPane newAttrPane( ICFFormManager formManager, ICFSecTableInfoObj argFocus ) {
		CFSecJavaFXTableInfoAttrPane retnew = new CFSecJavaFXTableInfoAttrPane( formManager, javafxSchema, argFocus );
		return( retnew );
	}

	public CFBorderPane newListPane( ICFFormManager formManager,
		ICFLibAnyObj argContainer,
		ICFSecTableInfoObj argFocus,
		Collection<ICFSecTableInfoObj> argDataCollection,
		ICFRefreshCallback refreshCallback,
		boolean sortByChain )
	{
		CFSecJavaFXTableInfoListPane retnew = new CFSecJavaFXTableInfoListPane( formManager,
			javafxSchema,
			argContainer,
			argFocus,
			argDataCollection,
			refreshCallback,
			sortByChain );
		return( retnew );
	}

	public CFBorderPane newPickerPane( ICFFormManager formManager,
		ICFSecTableInfoObj argFocus,
		ICFLibAnyObj argContainer,
		Collection<ICFSecTableInfoObj> argDataCollection,
		ICFSecJavaFXTableInfoChosen whenChosen )
	{
		CFSecJavaFXTableInfoPickerPane retnew = new CFSecJavaFXTableInfoPickerPane( formManager,
			javafxSchema,
			argFocus,
			argContainer,
			argDataCollection,
			whenChosen );
		return( retnew );
	}

	public CFTabPane newEltTabPane( ICFFormManager formManager, ICFSecTableInfoObj argFocus ) {
		CFSecJavaFXTableInfoEltTabPane retnew = new CFSecJavaFXTableInfoEltTabPane( formManager, javafxSchema, argFocus );
		return( retnew );
	}

	public CFSplitPane newAddPane( ICFFormManager formManager, ICFSecTableInfoObj argFocus ) {
		CFSecJavaFXTableInfoAddPane retnew = new CFSecJavaFXTableInfoAddPane( formManager, javafxSchema, argFocus );
		return( retnew );
	}

	public CFSplitPane newViewEditPane( ICFFormManager formManager, ICFSecTableInfoObj argFocus ) {
		CFSecJavaFXTableInfoViewEditPane retnew = new CFSecJavaFXTableInfoViewEditPane( formManager, javafxSchema, argFocus );
		return( retnew );
	}

	public CFBorderPane newAskDeleteForm( ICFFormManager formManager, ICFSecTableInfoObj argFocus, ICFDeleteCallback callback ) {
		CFSecJavaFXTableInfoAskDeleteForm retnew = new CFSecJavaFXTableInfoAskDeleteForm( formManager, javafxSchema, argFocus, callback );
		return( retnew );
	}

	public CFBorderPane newFinderForm( ICFFormManager formManager ) {
		CFSecJavaFXTableInfoFinderForm retnew = new CFSecJavaFXTableInfoFinderForm( formManager, javafxSchema );
		return( retnew );
	}

	public CFBorderPane newPickerForm( ICFFormManager formManager,
		ICFSecTableInfoObj argFocus,
		ICFLibAnyObj argContainer,
		Collection<ICFSecTableInfoObj> argDataCollection,
		ICFSecJavaFXTableInfoChosen whenChosen )
	{
		CFSecJavaFXTableInfoPickerForm retnew = new CFSecJavaFXTableInfoPickerForm( formManager,
			javafxSchema,
			argFocus,
			argContainer,
			argDataCollection,
			whenChosen );
		return( retnew );
	}

	public CFBorderPane newAddForm( ICFFormManager formManager, ICFSecTableInfoObj argFocus, ICFFormClosedCallback closeCallback, boolean allowSave ) {
		CFSecJavaFXTableInfoAddForm retnew = new CFSecJavaFXTableInfoAddForm( formManager, javafxSchema, argFocus, closeCallback, allowSave );
		return( retnew );
	}

	public CFBorderPane newViewEditForm( ICFFormManager formManager, ICFSecTableInfoObj argFocus, ICFFormClosedCallback closeCallback, boolean cameFromAdd ) {
		CFSecJavaFXTableInfoViewEditForm retnew = new CFSecJavaFXTableInfoViewEditForm( formManager, javafxSchema, argFocus, closeCallback, cameFromAdd );
		return( retnew );
	}
}
