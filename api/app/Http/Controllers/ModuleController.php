<?php

namespace App\Http\Controllers;

use App\Models\Faculty;
use Illuminate\Http\Request;
use App\Models\Module;
use PhpParser\Node\Expr\AssignOp\Mod;

class ModuleController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return response()->json(Module::all());
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $faculty_id = $request->get('faculty_id');
        $faculty = Faculty::find($faculty_id);
        $facultyModules = $faculty->modules->pluck('name');
        $name = $request->get('name');
        if(!in_array($name, $facultyModules)){
            return response()->json(['data'], 201);
        }

        $request->validate([
            'name' => 'required',
            'lecturer' => 'required'
        ]);

        return response()->json(Module::create($request -> all()), 201);
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $module = Module::find($id);
        if(!$module){
            return response()->json(['Module does not exist.'], 404);
        }
        return response()->json($module);
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
        $module = Module::find($id);
        $module->update($request->all());
        return response()->json($module);

    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        return response()->json(Module::destroy($id));
    }/**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function search($name)
    {
        return response()->json(Module::where('name', 'like', '%'.$name.'%')->get());
    }
}
