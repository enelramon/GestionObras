using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GestionObras.Api.DAL;
using GestionObras.Api.Models;

namespace GestionObras.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TiposTrabajosController : ControllerBase
    {
        private readonly Contexto _context;

        public TiposTrabajosController(Contexto context)
        {
            _context = context;
        }

        // GET: api/TiposTrabajos
        [HttpGet]
        public async Task<ActionResult<IEnumerable<TiposTrabajos>>> GetTiposTrabajos()
        {
          if (_context.TiposTrabajos == null)
          {
              return NotFound();
          }
            return await _context.TiposTrabajos.ToListAsync();
        }

        // GET: api/TiposTrabajos/5
        [HttpGet("{id}")]
        public async Task<ActionResult<TiposTrabajos>> GetTiposTrabajos(int id)
        {
          if (_context.TiposTrabajos == null)
          {
              return NotFound();
          }
            var tiposTrabajos = await _context.TiposTrabajos.FindAsync(id);

            if (tiposTrabajos == null)
            {
                return NotFound();
            }

            return tiposTrabajos;
        }

        // PUT: api/TiposTrabajos/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutTiposTrabajos(int id, TiposTrabajos tiposTrabajos)
        {
            if (id != tiposTrabajos.TipoTrabajoId)
            {
                return BadRequest();
            }

            _context.Entry(tiposTrabajos).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!TiposTrabajosExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/TiposTrabajos
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<TiposTrabajos>> PostTiposTrabajos(TiposTrabajos tiposTrabajos)
        {
          if (_context.TiposTrabajos == null)
          {
              return Problem("Entity set 'Contexto.TiposTrabajos'  is null.");
          }
            _context.TiposTrabajos.Add(tiposTrabajos);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetTiposTrabajos", new { id = tiposTrabajos.TipoTrabajoId }, tiposTrabajos);
        }

        // DELETE: api/TiposTrabajos/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteTiposTrabajos(int id)
        {
            if (_context.TiposTrabajos == null)
            {
                return NotFound();
            }
            var tiposTrabajos = await _context.TiposTrabajos.FindAsync(id);
            if (tiposTrabajos == null)
            {
                return NotFound();
            }

            _context.TiposTrabajos.Remove(tiposTrabajos);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool TiposTrabajosExists(int id)
        {
            return (_context.TiposTrabajos?.Any(e => e.TipoTrabajoId == id)).GetValueOrDefault();
        }
    }
}
