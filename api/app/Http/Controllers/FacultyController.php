<?php

namespace App\Http\Controllers;
use App\Models\Faculty;

use Illuminate\Http\Request;

class FacultyController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return response()->json(Faculty::all());
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $faculty = Faculty::find($request->get('id'));
        if($faculty)
            return response()->json(['data'], 201);

        $request->validate([
            'name' => 'required',
            'id' => 'required',
            'university' => 'required',
            'city' => 'required'
        ]);

        return response()->json(Faculty::create($request -> all()), 201);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $faculty = Faculty::find($id);
                if(!$faculty){
                    return response()->json(['Faculty does not exist.'], 404);
                }
        return response()->json($faculty);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $faculty = Faculty::find($id);
        $faculty->update($request->all());
        return response()->json($faculty);
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return response()->json(Faculty::destroy($id));
    }
}
