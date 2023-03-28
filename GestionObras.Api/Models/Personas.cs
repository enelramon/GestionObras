﻿using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class Personas
    {
        [Key]
        public int PersonaId { get; set; }
        public string? Nombres { get; set; }
        public string? Telefono { get; set; }
    }
}
