<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Faculty extends Model
{
    use HasFactory;
    protected $fillable = [
        'id',
        'name',
        'university',
        'city',
    ];
    public function faculties()
    {
        return $this->hasMany(Module::class);
    }
}
