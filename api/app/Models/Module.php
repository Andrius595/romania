<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Module extends Model
{
    use HasFactory;
    protected $fillable = [
        'name',
        'faculty',
        'lecturer',
    ];
    public function faculty()
    {
        return $this->belongsTo(Faculty::class,);
    }
}
